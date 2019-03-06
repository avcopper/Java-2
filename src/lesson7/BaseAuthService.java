package lesson7;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private List<User> users;

    public BaseAuthService() {
        users = new ArrayList<>();
        users.add(new User("login1", "pass1", "nick1"));
        users.add(new User("login2", "pass2", "nick2"));
        users.add(new User("login3", "pass3", "nick3"));
    }

    @Override
    public void start() {
        System.out.println("Auth service started");
    }

    @Override
    public void stop() {
        System.out.println("Auth service stoped");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (User user : users) {
            if (login.equals(user.getLogin()) && pass.equals(user.getPass())) return user.getNick();
        }
        return null ;
    }
}
