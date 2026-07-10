package net.myselfDeb.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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

//We have to map ORM with the database, 
//so we have to use @Document annotation to map the entity with the database. 
//It is used to specify a domain object that we want to persist in the database.

@Document (collection = "journalEntries")  //The collection attribute is used to specify the name of the MongoDB collection that will be used to store instances of the annotated class.
@Getter //The @Getter annotation is used to automatically generate getter methods for the fields of the class.
@Setter //The @Setter annotation is used to automatically generate setter methods for the fields of the class.
@NoArgsConstructor //The @NoArgsConstructor annotation is used to automatically generate a no-argument constructor for the class.
@AllArgsConstructor //The @AllArgsConstructor annotation is used to automatically generate a constructor with parameters for all fields of the class.
@ToString //The @ToString annotation is used to automatically generate a toString() method for the class, which provides a string representation of the object.
@EqualsAndHashCode //The @EqualsAndHashCode annotation is used to automatically generate equals() and hashCode() methods for the class, which are used to compare objects and generate hash codes based on their fields.
@Builder //The @Builder annotation is used to implement the builder pattern for the class, allowing for a more readable and flexible way to create instances of the class.
@Data //The @Data annotation is a convenient shortcut annotation that combines the functionality of @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor annotations. It generates all the boilerplate code for the class, including getters, setters, toString(), equals(), hashCode(), and a constructor for final fields.




public class JournalEntry {
    @Id                    //The @Id annotation is used to mark a field in a class as the primary key for that class.
    private ObjectId id;
    @NonNull              //The @NonNull annotation is used to indicate that the annotated field cannot be null. It is a way to enforce that a value must be provided for this field when creating or updating a JournalEntry object.
    private String title;
    private String content;
    private LocalDateTime date;



//here we are using Lombok library to generate getter and setter methods for the fields of the class,
//so we don't need to write getter and setter methods manually.

    // public ObjectId getId() {
    //     return id;
    // }

    // public void setId(ObjectId id) {
    //     this.id = id;
    // }

    // public String getTitle() {
    //     return title;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public String getContent() {
    //     return content;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    // public LocalDateTime getDate() {
    //     return date;
    // }

    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }
}
