package net.myselfDeb.journalApp.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.myselfDeb.journalApp.entity.JournalEntry;
import net.myselfDeb.journalApp.entity.User;
import net.myselfDeb.journalApp.service.JournalEntryService;
import net.myselfDeb.journalApp.service.UserService;

@RestController //The @RestController annotation is a specialized version of the @Controller annotation in Spring Framework. It is used to create RESTful web services and APIs.
@RequestMapping("/journal") //RequestMapping annotation is used to map web requests to specific handler classes or handler methods.

public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService; 
    //why EntryService is used here because we want to use the methods of the service to perform CRUD operations on the database.

    @Autowired
    private UserService userService;
    //why UserService is used here because we want to use the methods of the service to perform CRUD operations on the database.












    // LECTURE : 14 AND 15 (CONCEPT OF RESPONSE ENTITY)

    // @GetMapping       //localhost:8080/journal------>>GET(Retrieving the Data)
    // public ResponseEntity<?> getAll() {   //This line of code defines a method named getAll that handles HTTP GET requests to the /journal endpoint.
    //     List<JournalEntry> all = journalEntryService.getAll(); //This line of code calls the getAll method of the journalEntryService to retrieve all journal entries from the database and assigns them to the variable entries as a list of JournalEntry objects.
        
    //     if (all != null && !all.isEmpty()) { //This line of code checks if the list of journal entries is not null and not empty.
    //         return new ResponseEntity<>(all, HttpStatus.OK); //If the list is not null and not empty, it returns a ResponseEntity with the retrieved journal entries and an HTTP status of OK (200).
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND); //This line of code returns a ResponseEntity with an HTTP status of NOT FOUND (404) to indicate that the list is null or empty.
    // }


     //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY)

    //  @GetMapping("{userName}")      //localhost:8080/journal------>>GET(Retrieving the Data)
    // public ResponseEntity<?> getAllJournalEntriesOfUsers(@PathVariable String userName) {   //This line of code defines a method named getAll that handles HTTP GET requests to the /journal endpoint.
    //     User user = userService.findByUserName(userName); //This line of code calls the findByUserName method of the userService to retrieve the user with the specified username from the database. If no user is found, it will return null.
    //     List<JournalEntry> all = user.getJournalEntries(); //This line of code calls the getJournalEntries method of the User entity to retrieve journal entries for the specific user and assigns them to the variable entries as a list of JournalEntry objects.

    //     if (all != null && !all.isEmpty()) { //This line of code checks if the list of journal entries is not null and not empty.
    //         return new ResponseEntity<>(all, HttpStatus.OK); //If the list is not null and not empty, it returns a ResponseEntity with the retrieved journal entries and an HTTP status of OK (200).
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND); //This line of code returns a ResponseEntity with an HTTP status of NOT FOUND (404) to indicate that the list is null or empty.
    // }


     //LECTURE : 21 (Spring Security - Authorization Enabled)

       @GetMapping     //localhost:8080/journal------>>GET(Retrieving the Data)
      public ResponseEntity<?> getAllJournalEntriesOfUsers() {   //This line of code defines a method named getAll that handles HTTP GET requests to the /journal endpoint.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // This line of code retrieves the Authentication object from the SecurityContextHolder. 
        String userName = authentication.getName(); //This line of code retrieves the username of the currently authenticated user from the Authentication object. 
        User user = userService.findByUserName(userName); //This line of code calls the findByUserName method of the userService to retrieve the user with the specified username from the database. If no user is found, it will return null.
        List<JournalEntry> all = user.getJournalEntries(); //This line of code calls the getJournalEntries method of the User entity to retrieve journal entries for the specific user and assigns them to the variable entries as a list of JournalEntry objects.

        if (all != null && !all.isEmpty()) { //This line of code checks if the list of journal entries is not null and not empty.
            return new ResponseEntity<>(all, HttpStatus.OK); //If the list is not null and not empty, it returns a ResponseEntity with the retrieved journal entries and an HTTP status of OK (200).
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //This line of code returns a ResponseEntity with an HTTP status of NOT FOUND (404) to indicate that the list is null or empty.
      }




















    // LECTURE : 14 AND 15 (CONCEPT OF RESPONSE ENTITY)

    // @PostMapping //localhost:8080/journal------>>POST(Create a new Data)
    // public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {   //This line of code defines a method named createEntry that handles HTTP POST requests to the /journal endpoint. It takes a JournalEntry object as a parameter, which is populated from the request body using the @RequestBody annotation.
        
    //     try {
    //     myEntry.setDate(LocalDateTime.now()); //This line of code sets the current date and time as the date of the journal entry before saving it to the database.
    //     journalEntryService.saveEntry(myEntry); //This line of code calls the saveEntry method of the journalEntryService to save the journal entry in the database.
    //     return new ResponseEntity<>(myEntry, HttpStatus.CREATED); //This line of code returns a ResponseEntity with the saved journal entry and an HTTP status of CREATED (201) to indicate that the entry was successfully created.
    //     }

    //     catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //This line of code returns a ResponseEntity with a null body and an HTTP status of BAD REQUEST (400) to indicate that an error occurred while trying to create the journal entry.
    //     }
    // }


    //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY)

    // @PostMapping("{userName}")//localhost:8080/journal------>>POST(Create a new Data)
    // public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {   //This line of code defines a method named createEntry that handles HTTP POST requests to the /journal endpoint. It takes a JournalEntry object as a parameter, which is populated from the request body using the @RequestBody annotation.
        
    //     try {
    
    //     journalEntryService.saveEntry(myEntry, userName); //This line of code calls the saveEntry method of the journalEntryService to save the journal entry in the database.
    //     return new ResponseEntity<>(myEntry, HttpStatus.CREATED); //This line of code returns a ResponseEntity with the saved journal entry and an HTTP status of CREATED (201) to indicate that the entry was successfully created.
    //     }

    //     catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //This line of code returns a ResponseEntity with a null body and an HTTP status of BAD REQUEST (400) to indicate that an error occurred while trying to create the journal entry.
    //     }
    // }


    // LECTURE : 21 (Spring Security - Authorization Enabled)

    @PostMapping            //localhost:8080/journal------>>POST(Create a new Data)
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {   //This line of code defines a method named createEntry that handles HTTP POST requests to the /journal endpoint. It takes a JournalEntry object as a parameter, which is populated from the request body using the @RequestBody annotation.
        
        try {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //This line of code retrieves the Authentication object from the SecurityContextHolder, which holds information about the currently authenticated user.
        String userName = authentication.getName();  //This line of code retrieves the username of the currently authenticated user from the Authentication object.
        journalEntryService.saveEntry(myEntry, userName); //This line of code calls the saveEntry method of the journalEntryService to save the journal entry in the database.
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED); //This line of code returns a ResponseEntity with the saved journal entry and an HTTP status of CREATED (201) to indicate that the entry was successfully created.
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //This line of code returns a ResponseEntity with a null body and an HTTP status of BAD REQUEST (400) to indicate that an error occurred while trying to create the journal entry.
        }
    }























    // LECTURE : 14 AND 15 (CONCEPT OF RESPONSE ENTITY)

    // @GetMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>GET(Retrieving the Data(Particular ID Getting))
    // public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) { //This line of code defines a method named getById that handles HTTP GET requests to the /journal/id/{myId} endpoint. It takes an ObjectId as a parameter, which is populated from the path variable using the @PathVariable annotation.
    //    // return journalEntryService.findById(myId).orElse(null); //This line of code calls the findById method of the journalEntryService to retrieve the journal entry with the specified ID from the database.
       
    //    Optional<JournalEntry> journalEntry = journalEntryService.findById(myId); //This line of code calls the findById method of the journalEntryService to retrieve the journal entry with the specified ID from the database and assigns it to the variable journalEntry as an Optional<JournalEntry> object.
        
    //     if (journalEntry.isPresent()) { //This line of code checks if the journal entry with the specified ID is present in the database.
    //        return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK); //If the journal entry is present, it returns a ResponseEntity with the journal entry and an HTTP status of OK (200).       
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the journal entry is not present, it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
    // }


    //LECTURE : 21 (Spring Security - Authorization Enabled)

    @GetMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>GET(Retrieving the Data(Particular ID Getting))
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) { //This line of code defines a method named getById that handles HTTP GET requests to the /journal/id/{myId} endpoint. It takes an ObjectId as a parameter, which is populated from the path variable using the @PathVariable annotation.
       
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //This line of code retrieves the Authentication object from the SecurityContextHolder, which holds information about the currently authenticated user.
       String userName = authentication.getName();  //This line of code retrieves the username of the currently authenticated user from the Authentication object.
       User user = userService.findByUserName(userName); //This line of code finds the user by their username.
       List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());  //Stream Api is used here to filter the journal entries of the user based on the provided ID.
       
       if (!collect.isEmpty()) {  //Checks if the list is not empty. If the list is empty, it means the journal entry with the specified ID is not present in the database.
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId); //This line of code calls the findById method of the journalEntryService to retrieve the journal entry with the specified ID from the database and assigns it to the variable journalEntry as an Optional<JournalEntry> object.
        
        if (journalEntry.isPresent()) { //This line of code checks if the journal entry with the specified ID is present in the database.
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK); //If the journal entry is present, it returns a ResponseEntity with the journal entry and an HTTP status of OK (200).       
        }
    } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the journal entry is not present, it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
}
    
    

















    // LECTURE : 14 AND 15 (CONCEPT OF RESPONSE ENTITY)

    // @DeleteMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>DELETE(Removing the Data(Particular ID))
    // public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) { //This line of code defines a method named deleteJournalEntryById that handles HTTP DELETE requests to the /journal/id/{myId} endpoint. It takes an ObjectId as a parameter, which is populated from the path variable using the @PathVariable annotation.
    //     journalEntryService.deleteById(myId); //This line of code calls the deleteById method of the journalEntryService to delete the journal entry with the specified ID from the database.
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT); //This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the entry was successfully deleted.
    // }
    
    //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY)

    // @DeleteMapping("id/{userName}/{myId}")  //localhost:8080/journal/id/1--------->>DELETE(Removing the Data(Particular ID))
    // public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) { //This line of code defines a method named deleteJournalEntryById that handles HTTP DELETE requests to the /journal/id/{myId} endpoint. It takes an ObjectId as a parameter, which is populated from the path variable using the @PathVariable annotation.
    //     journalEntryService.deleteById(myId, userName); //This line of code calls the deleteById method of the journalEntryService to delete the journal entry with the specified ID from the database.
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT); //This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the entry was successfully deleted.
    // }

    //LECTURE : 21 (Spring Security - Authorization Enabled)

    @DeleteMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>DELETE(Removing the Data(Particular ID))
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) { //This line of code defines a method named deleteJournalEntryById that handles HTTP DELETE requests to the /journal/id/{myId} endpoint. It takes an ObjectId as a parameter, which is populated from the path variable using the @PathVariable annotation.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  //This line of code retrieves the Authentication object from the SecurityContextHolder, which holds information about the currently authenticated user.
        String userName = authentication.getName();  //This line of code retrieves the username of the currently authenticated user from the Authentication object.
        boolean removed = journalEntryService.deleteById(myId, userName); //This line of code calls the deleteById method of the journalEntryService to delete the journal entry with the specified ID from the database.
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //This line of code returns a ResponseEntity with an HTTP status of NO CONTENT (204) to indicate that the entry was successfully deleted.
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the journal entry is not present, it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
    }





    

    
   // LECTURE : 14 AND 15 (CONCEPT OF RESPONSE ENTITY)

  //     @PutMapping("id/{id}")    //localhost:8080/journal/id/1--------->>PUT(Updating the existing Data(Particular ID))
  //     public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) { //This line of code defines a method named updateJournalById that handles HTTP PUT requests to the /journal/id/{id} endpoint. It takes an ObjectId and a JournalEntry object as parameters, which are populated from the path variable and request body using the @PathVariable and @RequestBody annotations, respectively.
  //         //journalEntryService.updateEntry(id, myEntry); //This line of code calls the updateEntry method of the journalEntryService to update the journal entry with the specified ID in the database.
  //         //return journalEntryService.findById(id).orElse(null); //This line of code retrieves the updated journal entry from the database and returns it.
  //         JournalEntry old = journalEntryService.findById(id).orElse(null); //This line of code retrieves the existing journal entry with the specified ID from the database and assigns it to the variable old. If no entry is found, old will be null.
        
  //         if (old != null) {
  //            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle()); //This line of code checks if the title of the new entry is not null and not an empty string. If it is valid, it updates the title of the old entry with the new title; otherwise, it keeps the old title.
  //            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent()); //This line of code checks if the content of the new entry is not null and not an empty string. If it is valid, it updates the content of the old entry with the new content; otherwise, it keeps the old content.
  //         journalEntryService.saveEntry(old); //This line of code calls the saveEntry method of the journalEntryService to save the updated old entry back to the database.
  //         return new ResponseEntity<>(old, HttpStatus.OK); //This line of code returns the updated old entry as the response of the updateJournalById method.
  //           }
  //         return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the old entry is null (i.e., no entry was found with the specified ID), it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
  //     }

  //LECTURE : 16 (LINK BETWEEN USER AND JOURNAL ENTRY)

//     @PutMapping("id/{userName}/{id}")    //localhost:8080/journal/id/1--------->>PUT(Updating the existing Data(Particular ID))
//     public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String userName) { //This line of code defines a method named updateJournalById that handles HTTP PUT requests to the /journal/id/{id} endpoint. It takes an ObjectId and a JournalEntry object as parameters, which are populated from the path variable and request body using the @PathVariable and @RequestBody annotations, respectively.
//         JournalEntry old = journalEntryService.findById(id).orElse(null); //This line of code retrieves the existing journal entry with the specified ID from the database and assigns it to the variable old. If no entry is found, old will be null.
        
//         if (old != null) {
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle()); //This line of code checks if the title of the new entry is not null and not an empty string. If it is valid, it updates the title of the old entry with the new title; otherwise, it keeps the old title.
//            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent()); //This line of code checks if the content of the new entry is not null and not an empty string. If it is valid, it updates the content of the old entry with the new content; otherwise, it keeps the old content.
//         journalEntryService.saveEntry(old); //This line of code calls the saveEntry method of the journalEntryService to save the updated old entry back to the database.
//         return new ResponseEntity<>(old, HttpStatus.OK); //This line of code returns the updated old entry as the response of the updateJournalById method
// }
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the old entry is null (i.e., no entry was found with the specified ID), it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
//     }

// }


//LECTURE : 21 (Spring Security Authorization Enabled)

@PutMapping("id/{id}")    //localhost:8080/journal/id/1--------->>PUT(Updating the existing Data(Particular ID))
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) { //This line of code defines a method named updateJournalById that handles HTTP PUT requests to the /journal/id/{id} endpoint. It takes an ObjectId and a JournalEntry object as parameters, which are populated from the path variable and request body using the @PathVariable and @RequestBody annotations, respectively.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // This line of code gets the authentication object from the security context.
        String userName = authentication.getName(); //This line of code gets the username from the authentication object.
        User user = userService.findByUserName(userName); //This line of code retrieves the user with the specified username from the database and assigns it to the variable user.
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());  //Stream Api is used here to filter the journal entries of the user based on the provided ID.
        if (!collect.isEmpty()) {  //Checks if the list is not empty. If the list is empty, it means the journal entry with the specified ID is not present in the database.
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id); //This line of code calls the findById method of the journalEntryService to retrieve the journal entry with the specified ID from the database and assigns it to the variable journalEntry as an Optional<JournalEntry> object.
        
        if (journalEntry.isPresent()) { //This line of code checks if the journal entry with the specified ID is present in the database.
            JournalEntry old = journalEntry.get();  // This line retrieves the actual JournalEntry object from the Optional, assuming it is present.
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle()); //This line of code checks if the title of the new entry is not null and not an empty string. If it is valid, it updates the title of the old entry with the new title; otherwise, it keeps the old title.
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent()); //This line of code checks if the content of the new entry is not null and not an empty string. If it is valid, it updates the content of the old entry with the new content; otherwise, it keeps the old content.
            journalEntryService.saveEntry(old); //This line of code calls the saveEntry method of the journalEntryService to save the updated old entry back to the database.
            return new ResponseEntity<>(old, HttpStatus.OK); //This line of code returns the updated old entry as the response of the updateJournalById method       
        }
    } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //If the old entry is null (i.e., no entry was found with the specified ID), it returns a ResponseEntity with an HTTP status of NOT FOUND (404).
    }

}
