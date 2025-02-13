package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // username을 사용해서 User 객체를 찾음
    Optional<User> findUserByUsername(String username);

    // email과 password를 사용해서 User 객체를 찾음
    Optional<User> findUserByEmailAndPassword(String email, String password);

    // id가 존재한다면 찾은 객체를 반환, 만약 존재하지 않다면 예외를 던짐
    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다."));
    }

    // username이 존재한다면 찾은 객체를 반환, 만약 존재하지 않다면 예외를 던짐
    default User findByUsernameOrElseThrow(String username) {
        return findUserByUsername(username)
                .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "username을 찾을 수 없습니다.")
                );
    }

    // email과 password가 일치하다면 찾은 객체를 반환, 일치하지 않다면 예외를 던짐
    default User findUserByEmailAndPasswordElseThrow(String email, String password) {
        return findUserByEmailAndPassword(email, password)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.")
                );
    }
}
