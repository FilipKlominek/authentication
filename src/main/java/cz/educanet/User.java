package cz.educanet;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;

public class User {
    private final int id;
    private String name;
    private String email;
    private String hashedPassword;
    private final LocalDate createdAt;
    private LocalDate updatedAt;

    public User(String name, String email, String unHashedPassword) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.hashedPassword = DigestUtils.sha256Hex(unHashedPassword);
        this.createdAt = LocalDate.now();
        this.updatedAt = this.createdAt;
    }

    public User(int id, String name, String email, String unHashedPassword, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = DigestUtils.sha256Hex((unHashedPassword));
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String newName, String newEmail, String newUnHashedPassword) {
        this.name = newName;
        this.email = newEmail;
        this.hashedPassword = DigestUtils.sha256Hex(newUnHashedPassword);
        this.updatedAt = LocalDate.now();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
