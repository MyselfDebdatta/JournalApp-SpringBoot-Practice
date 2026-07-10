package net.myselfDeb.journalApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
// This annotation marks the class as a configuration class
// which means that it contains bean definitions and configuration settings for the Spring application context.
// In this case, it is used to configure Spring Security for the application. 
// The @Configuration annotation is part of the Spring Framework and is used to indicate that a class declares one or more @Bean meth

@EnableWebSecurity
// This annotation enables Spring Security for the application.
// It allows us to configure security settings for the application, such as authentication and authorization rules.
// so, by adding this annotation to the class, we are enabling Spring Security for the application and allowing it to be configured


//LECTURE NO : 26 (PROFILES IN SPRINGBOOT)

//@Profile("dev")
// This annotation is used to specify the active profile for the application.
// In this case, it is used to specify that the application is running in the development profile.
public class SpringSecurity { 



    @Bean                                    // The @Bean annotation is used to indicate that a method produces a bean to be managed by the Spring container.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //This method defines the security filter chain for the application.
        
        return http.authorizeHttpRequests(request -> request              
                        .requestMatchers("/public/**").permitAll()          // Allow public access to these endpoints!
                        .requestMatchers("/user/**").authenticated()            // Allow users to be created without logging in!
                        .requestMatchers("/journal/**").authenticated()     // IMPORTANT: Journal entries are PRIVATE - require login!
                        .requestMatchers("/admin/**").hasRole("ADMIN")      // IMPORTANT: Only ADMIN can access admin endpoints!
                        .requestMatchers("/error").permitAll()              // IMPORTANT: allow Spring Boot to show error messages!
                        .anyRequest().authenticated())                    
                .httpBasic(Customizer.withDefaults())                     // The httpBasic(Customizer.withDefaults()) method is used to enable basic authentication for the application.
                .csrf(AbstractHttpConfigurer::disable)                    // The csrf(AbstractHttpConfigurer::disable) method is used to disable CSRF protection for the application.
                .build();                                                 // The build() method is used to build the security filter chain.
    }



    @Bean
    public PasswordEncoder passwordEncoder() {      // The passwordEncoder() method is used to create a PasswordEncoder bean, which is used to encode passwords. 
        return new BCryptPasswordEncoder();         // BCryptPasswordEncoder is a password encoding algorithm that is used to encode passwords. It is a password-based key derivation function that is used to store passwords in a secure manner.  
    }

}