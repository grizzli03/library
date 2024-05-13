package kg.alatoo.libraryapp.services.impl;

import jakarta.validation.constraints.Email;
import kg.alatoo.libraryapp.config.JwtService;
import kg.alatoo.libraryapp.entities.User;
import kg.alatoo.libraryapp.enums.Role;
import kg.alatoo.libraryapp.exception.BadRequestException;
import kg.alatoo.libraryapp.repositories.UserRepository;
import kg.alatoo.libraryapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailSenderService senderService;

    @Override
    public void register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent())
            throw new BadRequestException("this email is already exist!");
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        String confirmCode = UUID.randomUUID().toString();
        senderService.sendEmail(user.getEmail(), "Confirmation of the email: ",
                "http://localhost:8080/user/confirm/" + confirmCode);

        user.setConfirmCode(confirmCode);
        userRepository.save(user);
    }

    @Override
    public String confirm(String confirmCode) {
        Optional<User> user = userRepository.findByConfirmCode(confirmCode);
        if (user.isPresent()){
            user.get().setConfirmCode("confirmed");
            userRepository.save(user.get());
            return "confirmed successfully!";
        }
        return "pls refresh page, no user found!";
    }

    @Override
    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new BadRequestException("no user with this email!");
        if (!user.get().getConfirmCode().equals("confirmed"))
            throw new BadRequestException("confirm ur email pls!");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (Exception e){
            throw new BadRequestException("login or password incorrect!");
        }
        return jwtService.generateToken(user.get());
    }
}
