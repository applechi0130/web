package com.member.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.member.dto.LoginRequest;
import com.member.dto.RegisterRequest;
import com.member.entity.User;
import com.member.repository.UserRepository;
import com.member.service.UserService;
import com.member.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest request,
			BindingResult bindingResult) {
		// 註冊邏輯
		if (bindingResult.hasErrors()) {

			// 取得所有錯誤訊息並合併成字串
			String errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(";"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		try {
			userService.registerUser(request);
			return ResponseEntity.ok("註冊成功");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, BindingResult bindingResult) {
		// 登入邏輯
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(";"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		Optional<User> userOptional = userRepository.findByPhone(request.getPhone());
		if (userOptional.isEmpty()
				|| !passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號不存在或密碼錯誤");
		}

		User user = userOptional.get();

		String token = jwtUtil.generateToken(user.getPhone());
		Map<String, String> response = Map.of("token", token);
		return ResponseEntity.ok(response);
	}
}
