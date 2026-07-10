
//Controller ---> Service ---> Repository

package net.myselfDeb.journalApp.service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.UserRepository;

@Service
@Slf4j // LECURE NO : 27 (SPRINGBOOT LOGGING)
public class UserService {

    @SuppressWarnings("unused")
    @Autowired 
    private UserRepository userRepository; 
    //why we are using this repository here because we want to use the methods of the repository to perform CRUD operations on the database.


    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //This is used to encrypt the password.





    //LECTURE NO : 27 (LOGGING IN SPRING BOOT)
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    //We use the slf4j logger because it is a logging facade that provides a simple and flexible way to log messages to the console. 
    //LoggerFactory.getLogger() is a static method and utility class that returns a logger instance for the specified class.
    //Here we use private because we don't want to access this logger from outside the class.
    //Here we use static because we want to use this logger in all the methods of the class.
    //Here we use final because we don't want to change the logger instance.






    public boolean saveNewUser (User user) { 
    //This method is used to save a user to the database.
    //It takes a User object as a parameter and calls the save method of the userRepository to persist the user in the database.
        try {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // (Lecture 20 : Spring Security) This is used to encrypt the password.
        user.setRoles(Arrays.asList("USER")); // (Lecture 20)  : Setting the role of the user to USER.
        userRepository.save(user); //This is used to save the user to the database.
        return true;    
    }
        catch(Exception e) {
            log.info("hahahahahahaha"); //LECTURE NO : 27 (SPRINGBOOT LOGGING)
            //logger.error("Error Occured {} : ", user.getUserName(), e);
            log.error("hahahahahahaha"); //ERROR 
            log.warn("hahahahahahaha"); //WARN
            log.trace("hahahahahahaha"); //TRACE
            log.debug("hahahahahahaha"); //DEBUG
            return false;
        }    
    }








    //LECTURE : 22 (Admin Create Functionality)

     public void saveAdmin (User user) { 
    //This method is used to save a user to the database.
    //It takes a User object as a parameter and calls the save method of the userRepository to persist the user in the database.
        user.setPassword(passwordEncoder.encode(user.getPassword())); // (Lecture 20 : Spring Security) This is used to encrypt the password.
        user.setRoles(Arrays.asList("USER", "ADMIN")); // (Lecture 20)  : Setting the role of the user to USER.
        userRepository.save(user); //This is used to save the user to the database.
    }













    //LECTURE 20 (Spring Security)

    public void saveUser (User user) { 
    //This method is used to save a new user to the database.
    //It takes a User object as a parameter and calls the save method of the userRepository to persist the user in the database.
        userRepository.save(user);
    }












    public List <User> getAll() {
    //This method is used to retrieve all users from the database.
    //It calls the findAll method of the userRepository to fetch all users from the database and returns them as a list of User objects.
        return userRepository.findAll();
    }












    public Optional <User> findById (ObjectId id) { 
    //This method is used to retrieve a user by its ID from the database.
    //It takes an ObjectId as a parameter and calls the findById method of the userRepository to fetch the user with the specified ID from the database and returns it as an Optional<User> object.
        return userRepository.findById(id);
    }












    public void deleteById (ObjectId id) {
    //This method is used to delete a user by its ID from the database.
    //It takes an ObjectId as a parameter and calls the deleteById method of the userRepository to remove the user with the specified ID from the database.
        userRepository.deleteById(id);
    }









    public User findByUserName(String userName) {
        //This method is used to retrieve a user by its username from the database.
        //It takes a String as a parameter and calls the findByUserName method of the
        return userRepository.findByUserName(userName);
    }







    
    public void updateUser (ObjectId id, User updatedUser) {
    //This method is used to update a user by its ID in the database.
    //It takes an ObjectId and a User object as parameters, sets the ID of the updated user to the specified ID, and calls the save method of the userRepository to persist the updated user in the database.
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

}



