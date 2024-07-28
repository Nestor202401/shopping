package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByAccount(String account);
	User findByVerificationCode(String verificationCode);
}
