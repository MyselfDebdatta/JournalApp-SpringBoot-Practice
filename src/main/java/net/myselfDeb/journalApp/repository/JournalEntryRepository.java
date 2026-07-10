package net.myselfDeb.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
 
import net.myselfDeb.journalApp.entity.JournalEntry;

//What is MongoRepository?
//Spring Data MongoDB provides the MongoRepository interface,which is a part of the Spring Data framework. 
//It is a specialized extension of the Repository interface
//that provides CRUD (Create, Read, Update, Delete) operations and additional methods for working with MongoDB databases.


public interface JournalEntryRepository extends MongoRepository <JournalEntry,ObjectId> {

}
