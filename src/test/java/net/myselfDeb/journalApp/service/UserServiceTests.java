package net.myselfDeb.journalApp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.UserRepository;

// LECTURE : 24 (JUnit Testing in SpringBoot)


@SpringBootTest
//This annotation is used to mark a class as a test class.
// It start the ApplicationContext.
// Without this annotation, the test will not run. Give NullPointerException.
// Why NullPointerException? 
// Because we are using the @Autowired annotation.
// This annotation is used to inject the dependencies.
public class UserServiceTests { 
// This is a unit test. This is used to test the UserService class.

    @Autowired 
    private UserRepository userRepository;
    //why we need this because we want to use the methods of the UserService class.

 
    @Autowired
    private UserService userService;
    //why we need this because we want to use the methods of the UserService class.









    @Test 
    //The @Test annotation is used to mark a method as a test method.
    public void testFindByUserName() {
    //This is the main part. We are testing the findByUserName method of the UserService class. 
        
        assertEquals(4, 2+2);        // The assertEquals method is used to assert that two values are equal.
        
        assertNotNull(userRepository.findByUserName("Debdatta")); //The assertNotNull method is used to assert that a value is not null.
        //assertTrue(userRepository.getJournalEntires().isEmpty()); //The assertTrue method is used to assert that a value is true.
    
    }







    // The use of custom parameter using Argument Source Provider

    @Disabled
    //Disabled is used to disable a test method.
    // This is useful when a test method is not working or we want to skip it.
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }
    








    @ParameterizedTest
    //We can run this test method multiple times with different values.
    // It differs from @Test because it runs multiple times with different values.
    // @Test runs only once.

    @CsvSource({  
    // This is a source of data for the test.
    // It is used to provide different values to the test method.
        "1, 2, 3",  
        "2, 3, 5",
        "3, 4, 7"
    })
    
    
    public void test(int a, int b, int expected) {
        assertEquals(expected, a+b, "The sum of " + a + " and " + b + " should be " + expected);   
    }





 @BeforeEach
 //The @BeforeEach annotation is used to mark a method to be executed before each test method.
 // This is useful for setting up the test environment.
 // This method will run before each test method.
    @SuppressWarnings("unused")
 public void setup() {

 }

 @BeforeAll
 //The @BeforeAll annotation is used to mark a method to be executed before all test methods.
 // This is useful for setting up the test environment.
 // This method will run before all test methods.
    @SuppressWarnings("unused")
 public static void setupAll() {

 }

 @AfterEach
 //The @AfterEach annotation is used to mark a method to be executed after each test method.
 // This is useful for cleaning up the test environment.
 // This method will run after each test method.
    @SuppressWarnings("unused")
 public void tearDown() {

 }

 @AfterAll
 //The @AfterAll annotation is used to mark a method to be executed after all test methods.
 // This is useful for cleaning up the test environment.
 // This method will run after all test methods.
    @SuppressWarnings("unused")
 public static void tearDownAll() {

 }





    
}
 