package kg.alatoo.libraryapp.services;

public interface AuthService {
    void register(String email, String password);

    String confirm(String confirmCode);

    String login(String email, String password);
}
