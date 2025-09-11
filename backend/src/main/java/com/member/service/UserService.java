package com.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.dto.RegisterRequest;
import com.member.entity.User;
import com.member.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(RegisterRequest request) {
		Integer phone = request.getPhone();
		String userName = request.getUsername();
		String email = request.getEmail();
		String rawPassword = request.getPassword();

		if (userRepository.findByPhone(phone).isPresent()) {
			throw new RuntimeException("手機號碼已註冊");
		}

		User user = new User();
		user.setPhone(phone);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(rawPassword));
		return userRepository.save(user);
	}

}
