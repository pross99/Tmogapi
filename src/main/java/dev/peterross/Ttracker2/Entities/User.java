package dev.peterross.Ttracker2.Entities;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private String id;

    @Indexed// Require unique username
    private String username;
    private String password;
    private String charName;
    private String charServer;

    public User(String username, String encodedPassword, String charName, String charServer) {
        this.username = username;
        this.password = encodedPassword;
        this.charName = charName;
        this.charServer = charServer;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCharServer() {
        return charServer;
    }

    public void setCharServer(String charServer) {
        this.charServer = charServer;
    }
    
}

