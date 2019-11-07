package com.revature.models;

import com.revature.passwordhash.PasswordHashing;
import java.util.Optional;

public class User {

    private int id;
    private String username;
    private String password;
    private String passwordSalt;
    private String firstName;
    private String lastName;
    private String email;
    private int roleId;

    public User() {
    }

    // Modified constructor to remove id parameter. The reason is because id is automatically generated in the table as a user entry is added.
    // Also used the generate salt and hash to properly hash the password before storing it into state. Private helper functions found directly below
    // constructor definition.
    
    public User(String username, String password, String firstName, String lastName,
            String email, int roleId) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
        
        String salt = this.generateSalt();
        String hashedPassword = this.generateHash(password, salt);
        
        this.passwordSalt = salt;
        this.password = hashedPassword;
        
        
    }
    
    /*////////////////////////////////////////////
     * PASSWORD SALT AND HASHING
     *////////////////////////////////////////////
    
    /* 
     * REQUIRED: N/A
     * MODIFIES: this.passwordSalt
     * EFFECTS: Generates and returns a salt value
     */
    private String generateSalt() {
    	Optional<String> salt = PasswordHashing.generateSalt(512);
    	
    	return salt.get();
    }
    
    /*
     * REQUIRED: 
     * MODIFIES:
     * EFFECTS: Generates and returns a hashed password given a salt and naked user password
     */
    private String generateHash(String password, String salt) {
    	Optional<String> hashedPassword = PasswordHashing.hashPassword(password.toCharArray(), salt);
    	
    	return hashedPassword.get();
    }
    
    
    /*////////////////////////////////////////////
     * END OF PASSWORD SALT AND HASHING
     *////////////////////////////////////////////


    // Getters & Setters, To_String, Equals, Hash, etc.

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((passwordSalt == null) ? 0 : passwordSalt.hashCode());
        result = prime * result + roleId;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", passwordSalt=" + passwordSalt
                + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (passwordSalt == null) {
            if (other.passwordSalt != null)
                return false;
        } else if (!passwordSalt.equals(other.passwordSalt))
            return false;
        if (roleId != other.roleId)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

}
