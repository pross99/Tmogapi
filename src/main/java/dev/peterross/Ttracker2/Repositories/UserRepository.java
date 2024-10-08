package dev.peterross.Ttracker2.Repositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.peterross.Ttracker2.Entities.User;


public interface UserRepository extends MongoRepository<User, String> {

   Optional<User> findByUsername(String username);
    User  deleteByUsername (String username);
    
}
