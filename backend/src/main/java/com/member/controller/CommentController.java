package com.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.member.dto.CommentRequest;
import com.member.entity.Comment;
import com.member.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// 新增留言
	@PostMapping("")
	public ResponseEntity<Comment> addComment(@RequestBody CommentRequest request) {
		Comment comment = commentService.addComment(request);
		return ResponseEntity.ok(comment);
	}

	// 取得指定發文的所有留言
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Integer postId) {
		List<Comment> comments = commentService.getCommentsByPostId(postId);
		return ResponseEntity.ok(comments);
	}

	// 編輯留言
	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateComment(@PathVariable Integer commentId, @RequestBody CommentRequest request) {
		Comment updated = commentService.updateComment(commentId, request);
		return ResponseEntity.ok(updated);
	}

	// 刪除留言
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}
}
