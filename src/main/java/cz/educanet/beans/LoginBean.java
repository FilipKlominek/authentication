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

    private boolean isLoggedIn = false;

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

        if (this.name.equals("") || this.email.equals("") || this.unHashedPasswordTest.equals("")) return false;

        List<User> userList = usersRepository.getUsers();

        for (User u : userList) {

            System.out.println(u.getHashedPassword());
            System.out.println(this.hash(this.unHashedPasswordTest + u.getSalt()));

            if (u.getName().equals(this.name) && u.getEmail().equals(this.getEmail())) {
                if (u.getHashedPassword().equals(this.hash(this.unHashedPasswordTest + u.getSalt()))) {
                    this.isLoggedIn = true;
                    return true;
                }
            }
        }



        return false;
    }

    private String hash(String unHashedPassword) {
        return DigestUtils.sha256Hex(unHashedPassword);
    }

    public void logout() {
        this.name = "";
        this.email = "";
        this.unHashedPasswordTest = "";
        this.isLoggedIn = false;
    }
}
