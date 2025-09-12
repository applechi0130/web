package com.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 表示此類為設定類別，會被 Spring 容器管理
@EnableWebSecurity // 啟用 Spring Security 的網路安全功能
public class SecurityConfig {

	// 註冊 PasswordEncoder Bean 使用 BCrypt 雜湊算法加密密碼
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 預設強度為 10，也可自行調整
	}

	// 定義安全過濾器鏈，設定 HTTP 請求權限與安全策略
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // 禁用 CSRF 保護

				// 設定 URL 權限規則，/register/** 全開放，其它皆須驗證登入
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/register/**").permitAll() // 註冊相關請求不需身份驗證
						.anyRequest().authenticated()) // 其他請求需要登入授權
				.formLogin(form -> form.permitAll()) // 啟用表單登入，所有人皆可存取登入頁面
				.logout(logout -> logout.permitAll()); // 啟用登出功能，所有人皆可存取登出頁面

		return http.build(); // 建立並回傳 SecurityFilterChain 實例
	}

}
