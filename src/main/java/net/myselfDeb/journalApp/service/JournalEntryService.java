
//Controller ---> Service ---> Repository

package net.myselfDeb.journalApp.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.myselfDeb.journalApp.entity.JournalEntry;
import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.repository.JournalEntryRepository;

@Slf4j
@Service
//The @Service annotation is used to mark a Java class as a service component 
//so that the component-scanning mechanism of Spring can pick it up and pull it into the application context.
//We can also use @Component annotation instead of @Service annotation,
//but it is better to use @Service annotation because it is more specific and it is used to mark a class as a service component.
public class JournalEntryService {

    @Autowired 
    //Autowired annotation is used for automatic dependency injection in Spring framework.
    //It allows Spring to resolve and inject collaborating beans into our bean.

    private JournalEntryRepository journalEntryRepository; 
    //why we are using this repository here because we want to use the methods of the repository to perform CRUD operations on the database.



    @Autowired
    private UserService userService;
    //This line of code autowires the UserService dependency, allowing the JournalEntryService to use its methods.



    
    
    
    
    
    @Transactional // (LECTURE NO : 17) The @Transactional annotation is used to define the scope of a single database transaction. The database transaction happens inside the scope of a persistence context.
    // So, if any exception occurs during the execution of the method, the transaction will be rolled back, and no changes will be persisted to the database.
    //In this case, it ensures that both the journal entry and the user object are saved in a single transaction, maintaining data integrity.
    //FOR EXAMPLE : If the journal entry is saved successfully but there is an error while saving the user object, the transaction will be rolled back, and the journal entry will not be saved in the database.
    //This is important to maintain the consistency of the data in the database.
    //For this reason, we use @Transactional annotation to annotate the saveEntry method, which saves both the journal entry and the user object in a single transaction.

    //LECTURE NO : 16 (LINK BETWEEN USER AND JOURNAL ENTRY, SO WE NEED TO ADD USERNAME IN THE PARAMETER OF SAVEENTRY METHOD)

    public void saveEntry (JournalEntry journalEntry, String userName) { 
    //This method is used to save a journal entry to the database.
    //It takes a JournalEntry object and a User object as parameters and calls the save method of the journalEntryRepository to persist the entry in the database.
        
        try {
        User user = userService.findByUserName(userName);  //This line of code finds the user by their username.    
        journalEntry.setDate(LocalDateTime.now()); //This line of code sets the date of the journal entry to the current date and time using the LocalDateTime.now() method. It ensures that the entry is timestamped with the exact moment it was created or saved.
        JournalEntry saved = journalEntryRepository.save(journalEntry); //This line of code calls the save method of the journalEntryRepository to persist the journal entry in the database. It saves the entry and returns the saved entry, which is assigned to the variable saved.
        user.getJournalEntries().add(saved); //This line of code adds the saved journal entry
       //user.setUserName(null); // (LECTURE NO : 17) This line of code sets the username of the user object to null. It is done to prevent the username from being saved again when the user object is saved later. This is necessary because the username is already stored in the database and does not need to be updated.
        userService.saveUser(user); //This line of code calls the saveEntry method of the userService to save the updated user object, which now includes the newly added journal entry. It ensures that the user's journal entries are persisted in the database. 
    }
        catch (Exception e) {
            System.out.println("Error while saving journal entry: " + e.getMessage()); //This line of code prints an error message to the console if an exception occurs while saving the journal entry. It helps in debugging and identifying issues during the save operation.
            throw new RuntimeException("Error while saving journal entry: " + e.getMessage()); //This line of code throws a new RuntimeException with a message indicating that an error occurred while saving the journal entry. It propagates the exception to the caller, allowing it to be handled appropriately. 
        }
    }














    //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY, SO WE NEED TO ADD USERNAME IN THE PARAMETER OF SAVEENTRY METHOD)
    // WHY TWO SAVEENTRY METHODS ARE USED HERE BECAUSE ONE IS USED TO SAVE THE JOURNAL ENTRY AND ANOTHER IS USED TO SAVE THE USER OBJECT WITH THE UPDATED JOURNAL ENTRIES.

    public void saveEntry (JournalEntry journalEntry) { 
    //This method is used to save a journal entry to the database.
    //It takes a JournalEntry object and a User object as parameters and calls the save method of the journalEntryRepository to persist the entry in the database.
        journalEntryRepository.save(journalEntry); //This line of code calls the save method of the journalEntryRepository to persist the journal entry in the database. It saves the entry and returns the saved entry, which is assigned to the variable saved.
    }











    public List <JournalEntry> getAll() {
    //This method is used to retrieve all journal entries from the database.
    //It calls the findAll method of the journalEntryRepository to fetch all entries from the database and returns them as a list of JournalEntry objects.
        return journalEntryRepository.findAll();
    }












    public Optional <JournalEntry> findById (ObjectId id) { 
    //This method is used to retrieve a journal entry by its ID from the database.
    //It takes an ObjectId as a parameter and calls the findById method of the journalEntryRepository to fetch the entry with the specified ID from the database and returns it as an Optional<JournalEntry> object.
        return journalEntryRepository.findById(id);
    }













    //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY, SO WE NEED TO ADD USERNAME IN THE PARAMETER OF DELETEBYID METHOD)

    @Transactional //this is used to ensure that both the operations are performed in a single transaction
    public boolean deleteById (ObjectId id, String userName) {
    //This method is used to delete a journal entry by its ID and username from the database.
    //It takes an ObjectId and a String as parameters and calls the deleteById method of the journalEntryRepository to remove the entry with the specified ID from the database.
       boolean removed = false; 
       try {
        User user = userService.findByUserName(userName); //This line of code finds the user by their username.
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id)); //This line of code removes the journal entry with the specified ID from the user's list of journal entries. It uses the removeIf method to iterate through the list and remove the entry that matches the given ID.
       
        if(removed) {
        userService.saveUser(user); //This line of code calls the saveEntry method of the
        journalEntryRepository.deleteById(id);
        }
    }
    catch (Exception e) {
        log.error("Error while deleting journal entry: " + e.getMessage()); //This line of code prints an error message to the console if an exception occurs while deleting the journal entry. It helps in debugging and identifying issues during the delete operation.
        throw new RuntimeException("Error while deleting journal entry: " + e.getMessage()); //This line of code throws a new RuntimeException with a message indicating that an error occurred while deleting the journal entry. It propagates the exception to the caller, allowing it to be handled appropriately.
    }
    return removed;
}









    public void updateEntry (ObjectId id, JournalEntry updatedEntry) {
    //This method is used to update a journal entry by its ID in the database.
    //It takes an ObjectId and a JournalEntry object as parameters, sets the ID of the updated entry to the specified ID, and calls the save method of the journalEntryRepository to persist the updated entry in the database.
        updatedEntry.setId(id);
        journalEntryRepository.save(updatedEntry);
    }










    //LECTURE NO : 21 (SPRING SECURITY)

    public List<JournalEntry> findByUserName(String userName) {
    //This method is used to retrieve all journal entries for a specific user from the database.    
        User user = userService.findByUserName(userName); //This line of code finds the user by their username. 
        if (user != null) { //This line of code checks if the user with the specified username is found in the database.
            return user.getJournalEntries(); //This line of code returns the list of journal entries for the specified user.    
        }
        return null; //This line of code returns null if the user with the specified username is not found in the database.     
    }

}



