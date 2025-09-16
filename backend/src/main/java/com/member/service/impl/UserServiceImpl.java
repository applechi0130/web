package com.member.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.dto.RegisterRequest;
import com.member.entity.User;
import com.member.exception.CustomException;
import com.member.repository.UserRepository;
import com.member.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(RegisterRequest request) {
		validateNewUser(request);
		User user = buildUserFromRequest(request);
		return userRepository.save(user);
	}

	private void validateNewUser(RegisterRequest request) {

		// 檢查手機號碼是否已存在
		if (userRepository.findByPhone(request.getPhone()).isPresent()) {
			throw new CustomException("手機號碼已註冊"); // 可以自定義異常類
		}

		// 檢查 Email 是否已存在
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new CustomException("Email 已註冊"); // 可以自定義異常類
		}
	}

	private User buildUserFromRequest(RegisterRequest request) {
		User user = new User();
		user.setPhone(request.getPhone());
		user.setUserName(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword())); // 使用 BCrypt 加密密碼
		return user; // 保存用戶並返回
	}

	// 新增
	@Override
	public User createUser(User user) {
		// 直接儲存新User
		return userRepository.save(user);
	}

	// 刪除
	@Override
	public void deleteUserById(Integer id) {
		if (!userRepository.existsById(id)) {
			throw new CustomException("使用者不存在，無法刪除");
		}
		userRepository.deleteById(id);
	}

	// 修改
	@Override
	public User updateUser(Integer id, User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUserName(updatedUser.getUserName());
			user.setPhone(updatedUser.getPhone());
			user.setEmail(updatedUser.getEmail());
			// 如果需要更新密碼，可以另外處理加密
			if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			}
			return userRepository.save(user);
		}).orElseThrow(() -> new CustomException("使用者不存在，無法更新"));
	}

	// 查詢
	@Override
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}