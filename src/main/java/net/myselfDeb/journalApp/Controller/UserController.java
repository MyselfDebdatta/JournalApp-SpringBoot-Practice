package net.myselfDeb.journalApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.UserRepository;
import net.myselfDeb.journalApp.service.UserService;

@RestController 
// The @RestController annotation is a specialized version of the @Controller annotation in Spring Framework. It is used to create RESTful web services and APIs.
@RequestMapping("/user") 
// RequestMapping annotation is used to map web requests to specific handler classes or handler methods.

public class UserController {


   
    @Autowired
    private UserService userService;
    // why UserService is used here because we want to use the methods of the 
    // service to perform CRUD operations on the database.



    //LECTURE NO:20 (User Repository is Added here)

    @Autowired
    private UserRepository userRepository;
    // why UserRepository is used here because we want to use the methods of the
    // repository to perform CRUD operations on the database.








   // LECTURE NO : 20 (GET method is disabled as per the security)

    // @GetMapping // localhost:8080/user------>>GET(Retrieving the Data)
    // public List<User> getAllUsers() { // This line of code defines a method named getAllUsers that handles HTTP GET requests to the /user endpoint.
    //     return userService.getAll(); // This line of code calls the getAll method of the userService to retrieve all users from the database and returns them as a list of User objects.
    // }











    // LECTURE NO : 20 (POST method is shift to PublicController as per the security)

    // @PostMapping // localhost:8080/user------>>POST(Create a new Data)
    // public void createUser(@RequestBody User user) { 
    // // This line of code defines a method named createUser that handles HTTP POST requests to the /user endpoint. It takes a User object as a parameter, which is populated from the request body using the @RequestBody annotation.
    //     userService.saveEntry(user); // This line of code calls the saveEntry method of the userService to save the user in the database.
    // }











    // @PutMapping("/{userName}") // localhost:8080/user/{userName}------>>PUT(Update an existing Data)
    // public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User user) { 
    // // This line of code defines a method named updateUser that handles HTTP PUT requests to the /user/{userName} endpoint. 
    // // It takes a String parameter userName, which is extracted from the URL path using the @PathVariable annotation, and a User object as a parameter, which is populated from the request body using the @RequestBody annotation.
    //     User userInDb = userService.findByUserName(userName); // This line of code calls the findByUserName method of the userService to retrieve the user with the specified username from the database and assigns it to the variable userInDb. If no user is found, userInDb will be null
                                                              
    //     if (userInDb != null) {
    //         userInDb.setUserName(user.getUserName()); // This line of code sets the username of the userInDb object to the username of the user object received in the request body.It updates the username of the existing user in the database with the new value provided in the request.                                                                                                                                                
    //         userInDb.setPassword(user.getPassword()); // This line of code sets the password of the userInDb object to the password of the user object received in the request body. It updates the password of the existing user in the database with the new value provided in the request.
    //         userService.saveEntry(userInDb); // This line of code calls the saveEntry method of the userService to save the updated userInDb object in the database. It persists the changes made to the existing user in the database.
    //     }
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT); // This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the update operation was successful, but there is no content to return in the response body.                                                             
    // }


    // LECTURE NO : 20 (PUT method is Changed as per the security)

    @PutMapping                         // localhost:8080/user/{userName}------>>PUT(Update an existing Data)
    public ResponseEntity<?> updateUser(@RequestBody User user) { 
    // This line of code defines a method named updateUser that handles HTTP PUT requests to the /user/{userName} endpoint. 
    // It takes a String parameter userName, which is extracted from the URL path using the @PathVariable annotation, and a User object as a parameter, which is populated from the request body using the @RequestBody annotation.
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName); // This line of code calls the findByUserName method of the userService to retrieve the user with the specified username from the database and assigns it to the variable userInDb. If no user is found, userInDb will be null
        userInDb.setUserName(user.getUserName()); // This line of code sets the username of the userInDb object to the username of the user object received in the request body.It updates the username of the existing user in the database with the new value provided in the request.                                                                                                                                                
        userInDb.setPassword(user.getPassword()); // This line of code sets the password of the userInDb object to the password of the user object received in the request body. It updates the password of the existing user in the database with the new value provided in the request.
        userService.saveNewUser(userInDb); // This line of code calls the saveEntry method of the userService to save the updated userInDb object in the database. It persists the changes made to the existing user in the database.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the update operation was successful, but there is no content to return in the response body.                                                             
    }


    // LECTURE NO : 20 (Deleting a User)

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() { 
        // This line of code defines a method named deleteUserById that handles HTTP DELETE requests to the /user/{userName} endpoint. 
        // It takes a String parameter userName, which is extracted from the URL path using the @PathVariable annotation, and a User object as a parameter, which is populated from the request body using the @RequestBody annotation.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //Here we get the authentication object from the security context.
        userRepository.deleteByUserName(authentication.getName());  //Here we delete the user by their username.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the delete operation was successful, but there is no content to return in the response body.                                                             
    }


    // LECTURE NO : 29 (MASTER EXTERNAL API)

    @GetMapping
    public ResponseEntity<?> greetings() { 
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //Here we get the authentication object from the security context.
        
        return new ResponseEntity<>("Welcome " + authentication.getName(), HttpStatus.OK); // This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the delete operation was successful, but there is no content to return in the response body.                                                             
    }
}