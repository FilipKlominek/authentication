package cz.educanet;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;

public class User {
    private final int id;
    private String name;
    private String email;
    private String hashedPassword;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String salt;

    public User(String name, String email, String unHashedPassword) { //when creating new User, generate salt
        this.id = 0;
        this.name = name;
        this.email = email;
        this.hashedPassword = this.hash(unHashedPassword);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public User(int id, String name, String email, String hashedPassword, String salt) { //when getting existing users, get existing salt
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.salt = salt;
    }

    public User(int id, String name, String email, String unHashedPassword, LocalDateTime createdAt, LocalDateTime updatedAt, String salt) { //unused full constructor that doesn't work
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = this.hash(unHashedPassword);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.salt = salt;
    }

    public void update(String newName, String newEmail, String newUnHashedPassword) { //unused method to update account info
        this.name = newName;
        this.email = newEmail;
        this.hashedPassword = this.hash(newUnHashedPassword);
        this.updatedAt = LocalDateTime.now();
    }

    private String hash(String unHashedPassword) {
        return DigestUtils.sha256Hex(unHashedPassword + this.generateSalt());
    }

    private String generateSalt() {
        int length = 32;

        Random rn = new Random();

        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < length; i++) {
            salt.append((char) rn.nextInt());
        }

        this.salt = salt.toString();

        return salt.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
