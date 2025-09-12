package com.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.member.dto.PostRequest;
import com.member.entity.Post;
import com.member.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	// 新增發文
	@PostMapping("")
	public ResponseEntity<?> createPost(@RequestBody @Valid PostRequest request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			String errors = bindingResult.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage)
					.reduce((s1, s2) -> s1 + "; " + s2).orElse("參數錯誤");
			return ResponseEntity.badRequest().body(errors);
		}

		Post post = postService.createPost(request);
		return ResponseEntity.ok(post);
	}

	// 取得所有發文
	@GetMapping("")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postService.getAllPosts();
		return ResponseEntity.ok(posts);
	}

	// 編輯發文
	@PutMapping("/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable Integer postId, @RequestBody PostRequest request) {
		Post updatedPost = postService.updatePost(postId, request);
		return ResponseEntity.ok(updatedPost);
	}

	// 刪除發文
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}
}
