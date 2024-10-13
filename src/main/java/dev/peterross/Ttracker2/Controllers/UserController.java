package dev.peterross.Ttracker2.Controllers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.peterross.Ttracker2.Entities.User;
import dev.peterross.Ttracker2.Security.LoginRequest;
import dev.peterross.Ttracker2.Services.UserService;





@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins="http://localhost:3000") // NEEDED FOR CALLING THE API ENDPOINTS FROM LOCALHOST IN FRONT END PROJECT
public class UserController {

    @Autowired 
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
        userService.RegisterUser(user);
        return ResponseEntity.ok("User has been registered");
        //TODO: process POST request
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional <User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    
        if (user.isPresent()) {
            return ResponseEntity.ok("Success! GZ" + user.get().getUsername());
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
