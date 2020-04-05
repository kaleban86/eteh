package com.eteh.eteh.repository;

import com.eteh.eteh.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findAllByUsername(String username);
}
