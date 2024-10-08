package dev.peterross.Ttracker2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;

import dev.peterross.Ttracker2.Entities.User;
import dev.peterross.Ttracker2.Repositories.UserRepository;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


   public Optional<User> findUserByUsername (String username) {
    return userRepository.findByUsername(username);
  }
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User RegisterUser (User user) throws Exception {
  if(userRepository.findByUsername(user.getUsername()) != null) {
    throw new Exception("Username already in use");
  }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

  try {
    return userRepository.save(user);
}   catch (DuplicateKeyException e) {
  throw new Exception ("Username already exists (from databaase)");
}
  



}

public Optional<User> authenticate (String username, String password) {
  Optional<User> userOpt = userRepository.findByUsername(username);
  if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
    return userOpt;
  }
  return Optional.empty();
}

}

    