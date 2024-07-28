package com.users.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.users.model.User;
import com.users.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService; // 注入郵件服務

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        if (userRepository.findByAccount(user.getAccount()) != null) {
            throw new RuntimeException("帳號已存在");
        }
        // 進行密碼加密
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        // 生成驗證碼
        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);
        user.setVerified(false);

        // 保存用戶
        User createdUser = userRepository.save(user);

        // 發送驗證郵件
        emailService.sendVerificationEmail(user.getEmail(), verificationCode);

        return createdUser;
    }

    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setAccount(userDetails.getAccount());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword())); // 密碼加密
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
    
    public User verifyUser(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user != null) {
            user.setVerified(true);
            user.setVerificationCode(null);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean authenticate(String account, String password) {
        User user = userRepository.findByAccount(account);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
