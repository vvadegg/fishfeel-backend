package ru.fishfeel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fishfeel.domain.Role;
import ru.fishfeel.domain.Status;
import ru.fishfeel.domain.User;
import ru.fishfeel.repository.RoleRepository;
import ru.fishfeel.repository.UserRepository;
import ru.fishfeel.service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Vadim Chigorev
 * @version 1.0
 */


@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${user.default.status}")
    private Status defaultStatus;


    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        user.setRoles(roles);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(defaultStatus);
        try {
            User save = userRepository.save(user);
            return save;
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            e.printStackTrace();
            String ee = "111";
        }
        return new User();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String username) {
        final User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Не найден пользователь - " + username);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
//      todo throw Exception NotFoundUser
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
