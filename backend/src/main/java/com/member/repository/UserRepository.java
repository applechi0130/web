package com.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);
}
