package com.member.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {

	private Integer userId;
	private Integer postId;

	@NotBlank(message = "留言內容不能為空")
	private String content;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
