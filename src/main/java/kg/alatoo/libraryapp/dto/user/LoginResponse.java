package kg.alatoo.libraryapp.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;

    public LoginResponse(String s) {
        this.token = s;
    }
}
