package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.dto.user.LoginResponse;
import kg.alatoo.libraryapp.dto.user.RegisterRequest;
import kg.alatoo.libraryapp.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody RegisterRequest registerRequest){
        return userService.login(registerRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String code){
        return userService.confirm(code);
    }
}
