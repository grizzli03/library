package kg.alatoo.libraryapp.controllers;


import kg.alatoo.libraryapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public void register(@RequestParam String email, @RequestParam String password){
        authService.register(email, password);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String confirmCode){
        return authService.confirm(confirmCode);
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        return authService.login(email, password);
    }

}
