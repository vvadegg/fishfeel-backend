package ru.fishfeel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fishfeel.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
