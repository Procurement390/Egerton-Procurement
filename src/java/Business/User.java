
package Business;

import java.io.Serializable;

public class User implements Serializable{
    
    private String name;
    private String username;
    private String password;
    private String role;

    public User() {
    }
    
    //Login constructor

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //registration constructor

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    //setters and getters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
    
}
