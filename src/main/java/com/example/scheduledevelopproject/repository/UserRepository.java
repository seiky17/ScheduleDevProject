package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다."));
    }

    default User findByUsernameOrElseThrow(String username) {
        return findUserByUsername(username)
                .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "username을 찾을 수 없습니다.")
                );
    }
}
