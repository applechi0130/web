package com.una;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

// @SpringBootApplication: 主應用啟動註解，包含@Configuration、@EnableAutoConfiguration、@ComponentScan
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
/*
 * // @RestController: 標示為REST控制器，回傳JSON或物件
 * 
 * @RestController
 * public class UserController {
 * 
 * // @PostMapping: 處理POST請求
 * // @Valid: 用於觸發資料驗證。當 Spring 偵測到 @Valid 時，會自動驗證物件是否符合約束條件
 * 
 * @PostMapping("/users")
 * public ResponseEntity<String> createUser(@Valid @RequestBody UserDto user) {
 * return ResponseEntity.ok("User created successfully");
 * }
 * }
 * 
 * // DTO物件，用於接收並驗證輸入資料
 * public class UserDto {
 * 
 * @NotBlank(message = "姓名不能為空") // 非空字串驗證
 * private String name;
 * 
 * @NotBlank(message = "密碼不能為空")
 * private String password;
 * 
 * @Email(message = "Email 格式不正確") // 郵件格式驗證
 * 
 * @Size(max = 255, message = "Email 長度不得超過 255")
 * private String email;
 * 
 * // getter & setter 省略
 * }
 * 
 * // @ControllerAdvice: 定義全域例外處理器，攔截控制器異常
 * 
 * @ControllerAdvice
 * public class GlobalExceptionHandler {
 * 
 * // 處理驗證失敗異常
 * 
 * @ExceptionHandler(MethodArgumentNotValidException.class)
 * public ResponseEntity<String>
 * handleValidationException(MethodArgumentNotValidException ex) {
 * return ResponseEntity.badRequest().body("驗證錯誤: " + ex.getMessage());
 * }
 * 
 * // 處理其他一般異常
 * 
 * @ExceptionHandler(Exception.class)
 * public ResponseEntity<String> handleGeneralException(Exception ex) {
 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("伺服器錯誤: " + ex.getMessage());
 * }
 * }
 * 
 * // @Service: 標示業務邏輯服務類別
 * 
 * @Service
 * public class UserService {
 * // 業務邏輯寫這
 * }
 * 
 * // @Repository: 標示資料訪問層，包含資料庫異常轉換功能
 * 
 * @Repository
 * public interface UserRepository {
 * // 資料庫存取
 * }
 * 
 * // @Autowired: 自動注入依賴 Bean
 * 
 * @Component
 * public class SomeComponent {
 * 
 * private final UserService userService;
 * 
 * // 建構子注入 (推薦)
 * 
 * @Autowired
 * public SomeComponent(UserService userService) {
 * this.userService = userService;
 * }
 * }
 * 
 * // @Value: 注入配置屬性
 * 
 * @Component
 * public class ConfigReader {
 * 
 * @Value("${app.name:DefaultAppName}")
 * private String appName;
 * }
 */