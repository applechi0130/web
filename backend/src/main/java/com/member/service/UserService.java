package com.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.dto.RegisterRequest;
import com.member.entity.User;
import com.member.exception.CustomException;
import com.member.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(RegisterRequest request) {
		String phone = request.getPhone();
		String userName = request.getUsername(); // 修正方法名稱
		String email = request.getEmail(); // 修正方法名稱
		String rawPassword = request.getPassword(); // 修正方法名稱

		// 檢查手機號碼是否已存在
		if (userRepository.findByPhone(phone).isPresent()) {
			throw new CustomException("手機號碼已註冊"); // 可以自定義異常類
		}

		// 檢查 Email 是否已存在
		if (userRepository.findByEmail(email).isPresent()) {
			throw new CustomException("Email 已註冊"); // 可以自定義異常類
		}

		User user = new User();
		user.setPhone(phone);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(rawPassword)); // 使用 BCrypt 加密密碼
		return userRepository.save(user); // 保存用戶並返回
	}

}
