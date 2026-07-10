package net.myselfDeb.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.service.UserService;

@RestController 
//RestController annotation is used to define a controller class in Spring MVC 
//that handles HTTP requests and returns responses in a RESTful manner.
@RequestMapping("/public")  //Requestmapping annotation is used to map web requests to specific handler classes or handler methods.
public class PublicController {



    @Autowired
    private UserService userService;
    

    
    @GetMapping("/health-check")   //GetMapping annotation is used to map HTTP GET requests to a specific method in the controller class.
    public String healthCheck() {  //localhost:8080/health-check
        return "OK";
    }




    @PostMapping("/create-user") // localhost:8080/user------>>POST(Create a new Data)
       public void createUser(@RequestBody User user) { 
       // This line of code defines a method named createUser that handles HTTP POST requests to the /user endpoint. It takes a User object as a parameter, which is populated from the request body using the @RequestBody annotation.
          userService.saveNewUser(user); // This line of code calls the saveEntry method of the userService to save the user in the database.
    }
}
