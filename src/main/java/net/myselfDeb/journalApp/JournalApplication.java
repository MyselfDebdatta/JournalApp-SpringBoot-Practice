package net.myselfDeb.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
// Spring Boot application annotation that enables auto-configuration, component scanning, and configuration properties support.
// Spring boot application is a Java-based framework used to create stand-alone, production-grade Spring-based applications with minimal configuration.
// Spring boot application is built on top of the Spring framework and provides a set of conventions and defaults that make it easy to create and deploy applications quickly.
// Spring boot application is designed to simplify the development of microservices and web applications by providing a set of pre-configured templates and tools that can be used to create applications quickly and easily.
// Spring boot application is designed to be opinionated, meaning that it provides a set of conventions and defaults that are intended to make it easy to create applications quickly and easily. However, it also allows developers to override these defaults and customize the application as needed.
// Spring boot application is designed to be modular, meaning that it can be easily extended and customized by adding or removing modules as needed. This makes it easy to create applications that are tailored to specific use cases and requirements.
// So, in short, spring boot application is a framework that simplifies the development of Java-based applications by providing a set of conventions and defaults that make it easy to create and deploy applications quickly and easily.
// For this reason, we use @SpringBootApplication annotation to annotate the main class of the application, which is JournalApplication class in this case.


@EnableTransactionManagement // (LECTURE NO : 17)
// The @EnableTransactionManagement annotation is used to enable Spring's annotation-driven transaction management capability.
// It allows you to use the @Transactional annotation on your service methods to define transactional boundaries.
// When this annotation is present, Spring will automatically manage transactions for methods annotated with @Transactional, 
// ensuring that they are executed within a transactional context. 
// This is particularly useful for managing database operations and ensuring data consistency in your application.
//the @EnableTransactionManagement annotation is typically placed on a configuration class or the main application class to enable transaction management for the entire application context.



// PlatformTransactionManager is an interface in Spring that defines the contract for transaction management.
// It provides methods for starting, committing, and rolling back transactions. 
// The PlatformTransactionManager is responsible for coordinating transactions across different resources, such as databases or message queues. 
// It abstracts the underlying transaction management implementation, allowing developers to work with a consistent API regardless of the specific transaction management technology being used (e.g., JDBC, JPA, Hibernate).



//MongoTransactionManager is a specific implementation of the PlatformTransactionManager interface that is designed to work with MongoDB databases.
// It provides transaction management capabilities for MongoDB operations, allowing you to define transactional boundaries.









public class JournalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args); //This line starts the Spring Boot application and creates an application context.
		ConfigurableEnvironment environment = context.getEnvironment(); //This line gets the environment from the application context.
		System.out.println(environment.getActiveProfiles()[0]); // This will print the active profile, that is dev, test, or prod

		//SpringApplication.run() method is used to launch the Spring Boot application.
		//It takes the main application class and command-line arguments as parameters.
	}

    // (LECTURE NO : 17) 
	// Creating bean using @Bean annotation for MongoTransactionManager, 
	// because we need to use MongoTransactionManager to manage transactions in our application.

	@Bean //The @Bean annotation is used to indicate that a method produces a bean to be managed by the Spring container.

	public MongoTransactionManager add(MongoDatabaseFactory dbFactory) {   //This method defines a bean of type MongoTransactionManager, which is responsible for managing transactions in a MongoDB database. It takes a MongoDatabaseFactory as a parameter, which is used to create connections to the MongoDB database.
		return new MongoTransactionManager(dbFactory);                     //This line of code creates and returns a new instance of MongoTransactionManager, passing the provided MongoDatabaseFactory to its constructor. This allows the transaction manager to manage transactions for the specified MongoDB database.	
	}




}
