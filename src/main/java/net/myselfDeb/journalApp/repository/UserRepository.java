package net.myselfDeb.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.myselfDeb.journalApp.entity.User;




public interface UserRepository extends MongoRepository <User,ObjectId> {

    User findByUserName(String userName); // This is a method that is used to find a user by their username. 



    void deleteByUserName(String userName); // This is a method that is used to delete a user by their username. 
}
