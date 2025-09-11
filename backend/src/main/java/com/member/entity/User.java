package com.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;

	@Column(nullable = false, unique = true, length = 10)
	private Integer phone;

	@Column(nullable = false, length = 10)
	private String userName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password; // 儲存 BCrypt 加密後的密碼

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone2) {
		this.phone = phone2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
