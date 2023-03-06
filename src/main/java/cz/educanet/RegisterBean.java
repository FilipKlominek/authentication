package cz.educanet;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.sql.SQLException;

@Named
@SessionScoped
public class RegisterBean implements Serializable {

    private final UsersRepository usersRepository = new UsersRepository();
    private String name = "";
    private String email = "";
    private String unHashedPassword = "";
    private String confirmUnHashedPassword = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnHashedPassword() {
        return unHashedPassword;
    }

    public void setUnHashedPassword(String password) {
        this.unHashedPassword = password;
    }

    public String getConfirm() {
        return confirmUnHashedPassword;
    }

    public void setConfirm(String confirm) {
        this.confirmUnHashedPassword = confirm;
    }

    public boolean register() throws SQLException {

        if (!this.unHashedPassword.equals(this.confirmUnHashedPassword)) return false;

        usersRepository.addUser(this.name, this.email, this.unHashedPassword);

        return true;
    }
}
