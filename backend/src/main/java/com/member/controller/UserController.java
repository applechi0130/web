package com.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional; // 可選封裝類
import java.util.stream.Collectors; // 用於Stream操作收集

import org.springframework.http.HttpStatus; // HTTP狀態碼
import org.springframework.http.ResponseEntity; // HTTP響應封裝
import org.springframework.security.crypto.password.PasswordEncoder; // 密碼加密
import org.springframework.validation.BindingResult; // 參數驗證結果
import org.springframework.validation.ObjectError; // 錯誤訊息
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.dto.LoginRequest; // 前端請求DTO
import com.member.dto.RegisterRequest; // 前端請求DTO
import com.member.entity.User; // 資料庫實體
import com.member.service.UserService; // 業務服務介面
import com.member.util.JwtUtil; // JWT生成工具類

import jakarta.validation.Valid; // Bean Validation用來驗證RequestBody物件

@RestController
@RequestMapping("/api/users") // 建議加統一路徑前綴
public class UserController {

	// 宣告三個屬性，將在建構子注入，用於後續業務邏輯、JWT產生、密碼驗證
	private final UserService userService;
	private final JwtUtil jwtUtil1;
	private final PasswordEncoder passwordEncoder1;

	// 建構子注入（依賴注入），確保物件依賴由Spring容器管理提供
	public UserController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.jwtUtil1 = jwtUtil;
		this.passwordEncoder1 = passwordEncoder;
	}

	@PostMapping("/register") // 定義POST接口/register
	// 使用@RequestBody將請求JSON轉成RegisterRequest物件，並用@Valid觸發欄位驗證，BindingResult接收驗證結果
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest request,
			BindingResult bindingResult) {

		// 檢查驗證錯誤，將所有錯誤訊息串成一句字串並回傳400 Bad Request
		if (bindingResult.hasErrors()) {

			String errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(";"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		// 調用註冊業務，若成功回傳200 OK和訊息，若異常(例如重複帳號)回傳400和錯誤訊息。
		try {
			userService.registerUser(request);
			return ResponseEntity.ok("註冊成功");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/login") // 定義POST接口 /login，接收帶驗證的LoginRequest物件與驗證結果。
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, BindingResult bindingResult) {
		// 處理驗證錯誤
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(";"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		// 根據手機號碼查用戶，若沒找到或密碼不符，回傳401 Unauthorized及通用錯誤
		Optional<User> userOptional = userService.getUserByPhone(request.getPhone());
		if (userOptional.isEmpty()
				|| !passwordEncoder1.matches(request.getPassword(), userOptional.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號不存在或密碼錯誤");
		}

		// 驗證成功後產生JWT Token並包成Json格式回應
		User user = userOptional.get();
		String token = jwtUtil1.generateToken(user.getPhone());
		Map<String, String> response = Map.of("token", token);
		return ResponseEntity.ok(response);
	}

	@PostMapping // 建立一個新用戶，直接回傳儲存後的User（非註冊流程）
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping // 回傳所有用戶清單
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}") // 根據ID查詢用戶，成功回傳200和用戶，找不到回404
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		Optional<User> usOpt = userService.getUserById(id);
		return usOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}") // 更新用戶資料，成功回傳更新後資料，找不到用戶回404
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		try {
			User updateUser = userService.updateUser(id, user);
			return ResponseEntity.ok(updateUser);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}") // 刪除用戶，成功回204 No Content，找不到回404
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUserById(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
