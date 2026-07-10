package net.myselfDeb.journalApp.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.test.context.ActiveProfiles;

import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.UserRepository;

//LECTURE NO : 26 (PROFILES IN SPRING BOOT)

//@ActiveProfiles("dev")
//@ActiveProfiles is used to specify the active profile for the application.
// In this case, it is used to specify that the application is running in the development profile.
// so without this annotation spring boot can not find the SecurityConfiguration class.


// LECTURE : 25 (UNIT TESTING IN SPRINGBOOT USING MOCKITO - USER DETAILS SERVICE IMPLEMENTATION )

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {
    
    @InjectMocks //In place of @Autowired when we are testing with Mockito.
    // This annotation is used to inject the dependencies into the class.
    // It is used when we are testing a class that has a dependency on another class.
    //Instance automatically create with Mocks
    private UserDetailServiceImpl userDetailsService;


    @Mock
    //This annotation is used to mark a field as a mock object.
    //A mock object is a fake object that is used to test the code.
    //It is used to replace the actual object with a fake object.

    //@MockitoBean
    //@Mock : This annotation is used to create a mock object.
    //@MockBean : This annotation is used to create a mock object and inject it into the application context.
    // It is used when we are testing a class that has a dependency on another class.
    private UserRepository userRepository;



    //@BeforeEach //Why we need this because we want to use the @InjectMocks annotation.
    // This is useful for setting up the test environment.
    // This method will run before each test method.
    //@SuppressWarnings("unused")
    //void setup() {
       // MockitoAnnotations.initMocks(this);
    //}

    @Test
    void loadUserByUsername(){
       when(userRepository.findByUserName(ArgumentMatchers.anyString()))
       .thenReturn(User.builder()
       .userName("Debdatta Panda").password("Debdatta Panda")
       .roles(new ArrayList<>()).build());
       UserDetails user = userDetailsService.loadUserByUsername("Debdatta Panda");
       Assertions.assertNotNull(user);
    }

}
