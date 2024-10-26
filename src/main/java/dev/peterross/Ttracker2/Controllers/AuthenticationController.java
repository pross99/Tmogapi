/* package dev.peterross.Ttracker2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import dev.peterross.Ttracker2.Security.JwtUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;







@RestController
public class AuthenticationController {
    


@Autowired 
private AuthenticationManager authenticationManager;

@Autowired
JwtUtil jwtUtil;


@PostMapping("/authenticate")
public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception {
    try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
    } catch (BadCredentialsException e) {
        throw new Exception ("Incorrect username or password", e);
    }

    final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
}
 */