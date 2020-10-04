package ru.fishfeel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fishfeel.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
