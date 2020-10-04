package ru.fishfeel.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fishfeel.domain.User;
import ru.fishfeel.security.jwt.JwtUserFactory;
import ru.fishfeel.service.UserService;

import javax.inject.Inject;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Inject
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        return JwtUserFactory.create(user);
    }
}
