package fachrizal.microservices.multiplication.challenge;

import fachrizal.microservices.multiplication.user.User;
import lombok.*;

import javax.persistence.*;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 */
//@Entity annotation to mark this class as an object to be mapped to a database record
@Entity
//@Data annotation from lombok to groups equals and hashCode methods, toString, getters, and also setters
@Data
//JPA and Hibernate also require our entities to have a default, empty constructor, so we use @NoArgsConstructor
@NoArgsConstructor
//@Getter
//@ToString
//@EqualsAndHashCode
@AllArgsConstructor
public class ChallengeAttempt {
    @Id
    //We use a generated
    //value so Hibernate will fill in that field for us, getting the next value of
    //the sequence from the database
    @GeneratedValue
    private Long id;
    //When collecting our attempts from the data store, we have to tell
    //Hibernate when to collect the values for the nested user too, which are stored in a
    //different table. If we would set it to EAGER, the user data gets collected with the attempt.
    //With LAZY, the query to retrieve those fields will be executed only when we try to access
    //them.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
