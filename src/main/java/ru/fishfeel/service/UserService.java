package ru.fishfeel.service;

import ru.fishfeel.domain.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByEmail(String username);

    User findById(Long id);

    void delete(Long id);

}
