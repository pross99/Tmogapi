package dev.peterross.Ttracker2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.peterross.Ttracker2.Entities.User;
import dev.peterross.Ttracker2.Repositories.UserRepository;
import dev.peterross.Ttracker2.Security.SignupRequest;
import dev.peterross.Ttracker2.Utility.MessageResponse;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


   public Optional<User> findUserByUsername (String username) {
    return userRepository.findByUsername(username);
  }


  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  

  public ResponseEntity <?> registerUser (SignupRequest signupRequest) {

  try {
    System.out.println("Registering user: " +
    "Username:" + signupRequest.getUsername() +
    "charName:" + signupRequest.getCharName() +
    "Username:" + signupRequest.getCharServer());

    if(userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity 
        .badRequest()
        .body(new MessageResponse("ERROR: Username is already in use!"));
    }

    if(signupRequest.getCharName() == null | signupRequest.getCharName().trim().isEmpty()) {
      return ResponseEntity 
        .badRequest()
        .body(new MessageResponse("ERROR: Character name is required"));
    }

    if(signupRequest.getCharServer() == null | signupRequest.getCharServer().trim().isEmpty()) {
      return ResponseEntity 
        .badRequest()
        .body(new MessageResponse("ERRIR; Character server is required!"));
    }

     User user = new User(

      signupRequest.getUsername(),
      passwordEncoder.encode(signupRequest.getPassword()),
      signupRequest.getCharName(),
      signupRequest.getCharServer()
      
    ); 

      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse("User registered!")); 
    } catch (Exception e) {
      return ResponseEntity
      .internalServerError()
      .body(new MessageResponse("Error" + e.getMessage()));
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

    