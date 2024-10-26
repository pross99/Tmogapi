package dev.peterross.Ttracker2.Controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.peterross.Ttracker2.Entities.User;
import dev.peterross.Ttracker2.Security.JwtUtil;
import dev.peterross.Ttracker2.Security.LoginRequest;
import dev.peterross.Ttracker2.Security.SignupRequest;
import dev.peterross.Ttracker2.Services.UserService;





@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins="http://localhost:3000") // NEEDED FOR CALLING THE API ENDPOINTS FROM LOCALHOST IN FRONT END PROJECT
public class UserController {

    @Autowired 
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        try {
        userService.registerUser(signupRequest);
        return ResponseEntity.ok("User has been registered");
        
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // should get the user details first?
        Optional <User> user = userService.findUserByUsername(loginRequest.getUsername());
         try {
            // should only authenticate once with AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword())
        );

            // If getting here it means auth was successful
        SecurityContextHolder.getContext().setAuthentication(authentication);

            // generate JWT token

         String jwt = jwtUtil.generateToken(loginRequest.getUsername());
              
        


        if (!user.isPresent()) {

           return ResponseEntity
           .status(HttpStatus.UNAUTHORIZED)
           .body("User not found"); 
        }

                //create response
                 Map<String, String> responseBody  = new HashMap<>();
            responseBody.put("Message", "Login Successfull!");
            responseBody.put("token", jwt);
            responseBody.put("username", user.get().getUsername()); // RETURN USERNAME
            responseBody.put("userId", user.get().getId());

            return ResponseEntity.ok(responseBody);



            } catch (BadCredentialsException e) {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("INVALID USERNAME OR PASSWORD");
            } catch (Exception e) {
                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during login");
        }
    }
}
