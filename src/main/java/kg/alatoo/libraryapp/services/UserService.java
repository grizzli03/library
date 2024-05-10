package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.user.LoginResponse;
import kg.alatoo.libraryapp.dto.user.RegisterRequest;

public interface UserService {
    void register(RegisterRequest registerRequest);

    LoginResponse login(RegisterRequest registerRequest);

    String confirm(String code);
}
