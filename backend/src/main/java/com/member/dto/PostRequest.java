package com.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRequest {

	@NotNull(message = "使用者ID不能為空")
	private Integer userId;

	@NotBlank(message = "內容不能為空")
	@Size(max = 1000, message = "內容長度不能超過1000個字元")
	private String content;

	private String image;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
