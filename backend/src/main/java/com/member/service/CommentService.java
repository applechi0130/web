package com.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.dto.CommentRequest;
import com.member.entity.Comment;
import com.member.entity.Post;
import com.member.entity.User;
import com.member.repository.CommentRepository;
import com.member.repository.PostRepository;
import com.member.repository.UserRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public Comment addComment(CommentRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("使用者不存在"));

		Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("文章不存在"));

		Comment comment = new Comment();
		comment.setUser(user);
		comment.setPost(post);
		comment.setContent(request.getContent());
		comment.setCreatedAt(new Date());

		return commentRepository.save(comment);
	}

	public List<Comment> getCommentsByPostId(Integer postId) {
		return commentRepository.findByPost_PostIdOrderByCreatedAtAsc(postId);
	}

	// 編輯留言
	public Comment updateComment(Integer commentId, CommentRequest request) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("留言不存在"));

		comment.setContent(request.getContent());
		comment.setUpdatedAt(new Date());

		return commentRepository.save(comment);
	}

	// 刪除留言
	public void deleteComment(Integer commentId) {
		if (!commentRepository.existsById(commentId)) {
			throw new RuntimeException("留言不存在");
		}
		commentRepository.deleteById(commentId);
	}
}
