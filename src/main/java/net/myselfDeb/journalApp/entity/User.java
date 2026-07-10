package net.myselfDeb.journalApp.entity;



import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Document (collection = "users")  //The collection attribute is used to specify the name of the MongoDB collection that will be used to store instances of the annotated class.
@Getter //The @Getter annotation is used to automatically generate getter methods for the fields of the class.
@Setter //The @Setter annotation is used to automatically generate setter methods for the fields of the class.
@NoArgsConstructor //The @NoArgsConstructor annotation is used to automatically generate a no-argument constructor for the class.
@AllArgsConstructor //The @AllArgsConstructor annotation is used to automatically generate a constructor with parameters for all fields of the class.
@ToString //The @ToString annotation is used to automatically generate a toString() method for the class, which provides a string representation of the object.
@EqualsAndHashCode //The @EqualsAndHashCode annotation is used to automatically generate equals() and hashCode() methods for the class, which are used to compare objects and generate hash codes based on their fields.
@Builder //The @Builder annotation is used to implement the builder pattern for the class, allowing for a more readable and flexible way to create instances of the class.
@Data //The @Data annotation is a convenient shortcut annotation that combines the functionality of @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor annotations. It generates all the boilerplate code for the class, including getters, setters, toString(), equals(), hashCode(), and a constructor for final fields.




public class User {
    @Id                                           
    private ObjectId id;
    @Indexed(unique = true)   //The @Indexed annotation is used to create an index on the annotated field in the MongoDB collection. The unique attribute is set to true, which means that the values of this field must be unique across all documents in the collection.
    @NonNull                  //The @NonNull annotation is used to indicate that the annotated field cannot be null. It is a way to enforce that a value must be provided for this field when creating or updating a User object.
    private String userName;
    @NonNull
    private String password;

    @DBRef                    //The @DBRef annotation is used to indicate that the annotated field is a reference to another document in a different collection. In this case, it indicates that the journalEntries field is a reference to the JournalEntry documents in the "journalEntries" collection.
    @Builder.Default          // The @Builder.Default annotation is used to specify a default value for a field in a builder pattern. This ensures that even if no value is explicitly set for the journalEntries field when creating a User object using the builder, it will automatically be initialized with an empty ArrayList.
    private List <JournalEntry> journalEntries = new ArrayList<>(); //This line of code defines a field named journalEntries, which is a list of JournalEntry objects. It represents the journal entries associated with the user. The List interface is used to define a collection that can hold multiple JournalEntry objects, allowing for the storage and retrieval of a user's journal entries.
    private List<String> roles;  // (LECTURE NO : 20) The roles field is a list of strings that represents the roles of the user. A user entity to represent the user data model in the database.. 
} 