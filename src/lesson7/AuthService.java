package lesson7;

public interface AuthService {
    void start();

    void stop();

    String getNickByLoginPass(String login, String pass);
}
