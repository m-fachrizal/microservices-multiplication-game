package fachrizal.microservices.multiplication.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Stores information to identify the user.
 */
//@Entity annotation to mark this class as an object to be mapped to a database record
@Entity
//@Data annotation from lombok to groups equals and hashCode methods, toString, getters, and also setters
@Data
//JPA and Hibernate also require our entities to have a default, empty constructor, so we use @NoArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    //We use a generated
    //value so Hibernate will fill in that field for us, getting the next value of
    //the sequence from the database
    @GeneratedValue
    private Long id;
    private String alias;

    public User(final String userAlias){
        this(null, userAlias);
    }
}
