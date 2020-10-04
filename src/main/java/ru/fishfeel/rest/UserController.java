package ru.fishfeel.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.fishfeel.domain.User;
import ru.fishfeel.dto.AuthenticatedRequestDto;
import ru.fishfeel.repository.UserRepository;
import ru.fishfeel.security.jwt.JwtTokenProvider;
import ru.fishfeel.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Inject
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticatedRequestDto dto){

        try {
            String email = dto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, dto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("Пользователь " + email + " не найден");
            }

            String token = jwtTokenProvider.createtoken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверное имя пользователя или пароль");
        }

    }

    @PostMapping("/registration")
    public ResponseEntity registration(@Valid @RequestBody User user) {
        User register = userService.register(user);
        return ResponseEntity.ok(register);
    }


//    @GetMapping("/admin")
//    public List<User> index(){
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/users")
//    public List<User> index2(){
//        return userRepository.findAll();
//    }





}
