package net.myselfDeb.journalApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.myselfDeb.journalApp.service.UserService;

import java.util.List;

import net.myselfDeb.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;


//LECTURE : 22 (Admin functionality)

@RestController
@RequestMapping("/admin")        // this annotation is used to map the web requests to the controller class.
public class AdminController {   //@RestController is a specialized version of the @Controller annotation in Spring Framework.
    
    @Autowired
    private UserService userService;
    // here UserService is used to perform CRUD operations on the database.
    // Spring Data MongoDB automatically provides implementations for these methods. 
    // @Autowired annotation is used to inject the UserService dependency into the controller.



    @GetMapping("/all-users")                         // localhost:8080/admin/all-users
    public ResponseEntity<?> getAllUsers() {          // This line of code defines a method named getAllUsers that handles HTTP GET requests to the /admin/all-users endpoint.
        List<User> all = userService.getAll();        // This line of code calls the getAll method of the userService to retrieve all users from the database and assigns them to the variable all as a list of User objects.
        if(all != null && !all.isEmpty()) {           // This line of code checks if the list of users is not null and not empty.
            return new ResponseEntity<>(all,HttpStatus.OK); // If the list is not null and not empty, it returns a ResponseEntity with the retrieved users and an HTTP status of OK (200).
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // This line of code returns a ResponseEntity with an HTTP status of NOT FOUND (404) to indicate that the list is null or empty.
        
    }


    @PostMapping("/create-admin-user")                  // localhost:8080/admin/create-admin-user
    public void createUser(@RequestBody User user) {   // This line of code defines a method named createUser that handles HTTP POST requests to the /admin/create-admin-user endpoint.
        userService.saveAdmin(user);                   // This line of code calls the saveAdmin method of the userService to save the user to the database.
    }
}

