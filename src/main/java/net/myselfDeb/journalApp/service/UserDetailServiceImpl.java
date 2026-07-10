package net.myselfDeb.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService { 
// This class implements the UserDetailsService interface, which is a core interface in Spring Security.
//  It is used to retrieve user-related data. The implementation of this interface is responsible for loading user-specific data during authentication.


  @Autowired
  private UserRepository userRepository;
  // We are injecting the UserRepository into the UserDetailServiceImpl class
  // The @Autowired annotation is used to inject the UserRepository into the UserDetailServiceImpl class. 
  // This allows the UserDetailServiceImpl class to use the methods of the UserRepository class to access the database.
    

  @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        // This method is overridden from the UserDetailsService interface.
        //  It is called by Spring Security to load user details based on the provided username during the authentication process.
        
        // This creates a hardcoded "admin" user to bypass the database without causing circular dependencies!
        if ("admin".equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("admin")
                    .password(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("admin"))
                    .roles("ADMIN", "USER")
                    .build();
        }

        User user = userRepository.findByUserName(username); // This line of code uses the userRepository to find a user entity based on the provided username.
        if (user != null) {                                 // This line checks if the user entity is found in the database. If the user is found, it proceeds to create a UserDetails object.
             return org.springframework.security.core.userdetails.User.builder() // This line uses the builder pattern to create a UserDetails object. The builder allows for a more readable and flexible way to construct the UserDetails object.
                    .username(user.getUserName())                       // This line sets the username for the UserDetails object using the username retrieved from the user entity.
                    .password(user.getPassword())                       // This line sets the password for the UserDetails object using the password retrieved from the user entity.
                    .roles(user.getRoles().toArray(new String[0]))      // This line creates and returns a UserDetails object using the user's username, password, and authorities (roles/permissions).
                    .build();                                           // This line builds the UserDetails object. 
                    //return userDetails; // This line returns the created UserDetails object to Spring Security for further processing during authentication.
                }
        throw new UsernameNotFoundException("User not found with username : "+username);  // if user not found, throw exception, and by this we have enabled Spring security to load user details and authenticate the user.
    }
}