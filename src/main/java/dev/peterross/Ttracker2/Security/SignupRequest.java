package dev.peterross.Ttracker2.Security;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class SignupRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
    private String password;

    @NotBlank(message = "charName is required!")
    @Size(min = 3, max = 12, message = "Username must be between 3 and 12 characters")
    private String charName;

    @NotBlank(message = "Server is required")
    @Size(min = 3, max = 20, message = "Server name must be between 3 and 20 characters")
    private String charServer;

    // Constructor
    public SignupRequest() {
    }

    public SignupRequest(String username, String password, String charName, String charServer) {
        this.username = username;
        this.password = password;
        this.charName = charName;
        this.charServer = charServer;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharServer() {
        return charServer;
    }

    public void setCharServer(String charServer) {
        this.charServer = charServer;
    }


    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }



    // Optional: Override toString() (but exclude password for security)
    @Override
    public String toString() {
        return "SignupRequest{" +
            "username='" + username + '\'' +
            ", password='[PROTECTED]'" +
            ", charName='" + charName +
            ", charServer='" + charServer +
            '}';
    }

 
}