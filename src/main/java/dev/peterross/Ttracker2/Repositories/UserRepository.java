package dev.peterross.Ttracker2.Repositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.peterross.Ttracker2.Entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

   Optional<User> findByUsername(String username);
    User  deleteByUsername (String username);
    
}
