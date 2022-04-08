package fachrizal.microservices.multiplication.challenge;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The first type specifies what is the class of the returned entity, ChallengeAttempt
 * in our case. The second class must match the type of the index, which is a Long in our
 * repository (the id field)
 */
public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {

    /**
     * @return the last 10 attempts for a given user, identified by their alias.
     */
    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);
}
