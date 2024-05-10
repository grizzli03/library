package kg.alatoo.libraryapp.services.impl;

import kg.alatoo.libraryapp.config.JwtService;
import kg.alatoo.libraryapp.dto.user.LoginResponse;
import kg.alatoo.libraryapp.dto.user.RegisterRequest;
import kg.alatoo.libraryapp.enums.Role;
import kg.alatoo.libraryapp.repositories.UserRepository;
import kg.alatoo.libraryapp.services.EmailSender;
import kg.alatoo.libraryapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import kg.alatoo.libraryapp.entities.User;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    @Override
    public void register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setRole(Role.USER);
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        String confirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(confirmCode);
        emailSender.sendEmail(user.getEmail(), "Confirm your email", "http://localhost:8777/api/v1/public/user/confirm?code=" + user.getConfirmCode());
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));
        }
        catch (Exception e){
            throw new RuntimeException("Invalid email or password");
        }
        User user = userRepository.findByEmail(registerRequest.getEmail()).get();
        if(!user.getConfirmCode().equals("authenticated")){
            throw new RuntimeException("User not authenticated");
        }
        return new LoginResponse(jwtService.generateToken(user));
    }

    @Override
    public String confirm(String code) {
        User user = userRepository.findByConfirmCode(code).orElseThrow(() -> new RuntimeException("User not found"));
        user.setConfirmCode("authenticated");
        userRepository.save(user);
        return "User authenticated successfully";
    }
}
