package net.myselfDeb.journalApp.Controller;

// import net.myselfDeb.journalApp.entity.JournalEntry;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.*;

// @RestController
// @RequestMapping("/_journal")
// public class JournalEntryController {

//     private Map<Long, JournalEntry> journalEntries = new HashMap<>();

//     @GetMapping
//     public List<JournalEntry> getAll() {    //localhost:8080/journal------>>GET(Retrieving the Data)
//         return new ArrayList<>(journalEntries.values());
//     }

//     @PostMapping
//     public boolean createEntry(@RequestBody JournalEntry myEntry) {   //localhost:8080/journal--------->>POST(Create a new Data)
//         journalEntries.put(myEntry.getId(), myEntry);
//         return true;
//     }

//     @GetMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>GET(Retrieving the Data(Particular ID Getting))
//     public JournalEntry getById(@PathVariable Long myId) {
//         return journalEntries.get(myId);
//     }

//     @DeleteMapping("id/{myId}")  //localhost:8080/journal/id/1--------->>DELETE(Removing the Data(Particular ID))
//     public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
//         return journalEntries.remove(myId);
//     }

//     @PutMapping("id/{id}")    //localhost:8080/journal/id/1--------->>PUT(Updating the existing Data(Particular ID))
//     public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
//         return journalEntries.put(id, myEntry);
//     }



// }
