package cz.educanet.beans;

import cz.educanet.User;
import cz.educanet.UsersRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private boolean isLoggedIn = true;

    private final UsersRepository usersRepository = new UsersRepository();
    private String name = "";
    private String email = "";
    private String unHashedPasswordTest = "";

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

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

    public String getUnHashedPasswordTest() {
        return unHashedPasswordTest;
    }

    public void setUnHashedPasswordTest(String unHashedPasswordTest) {
        this.unHashedPasswordTest = unHashedPasswordTest;
    }

    public boolean login() throws SQLException {

        if (this.name.equals("") || this.email.equals("") || this.unHashedPasswordTest.equals(""))
            return false;

        List<User> userList = usersRepository.getUsers();

        User user = new User(this.name, this.email, this.unHashedPasswordTest);

        for (User u : userList) {
            if (u.getName().equals(user.getName()) && u.getEmail().equals(user.getEmail()) && u.getHashedPassword().equals(user.getHashedPassword())) {
                System.out.println("yes");
                this.isLoggedIn = true;
                return true;
            }
        }

        System.out.println("65f1gbf6");

        return false;
    }

    public void logout() {
        this.isLoggedIn = false;
    }
}
