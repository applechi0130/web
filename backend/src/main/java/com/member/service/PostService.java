package com.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.dto.PostRequest;
import com.member.entity.Post;
import com.member.entity.User;
import com.member.repository.PostRepository;
import com.member.repository.UserRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public Post createPost(PostRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("使用者不存在"));

		Post post = new Post();
		post.setUser(user);
		post.setContent(request.getContent());
		post.setImage(request.getImage());
		post.setCreatedAt(new Date());

		return postRepository.save(post);
	}

	public List<Post> getAllPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc();
	}

	public Post updatePost(Integer postId, PostRequest request) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("發文不存在"));
		post.setContent(request.getContent());
		post.setImage(request.getImage());
		return postRepository.save(post);
	}

	public void deletePost(Integer postId) {
		if (!postRepository.existsById(postId)) {
			throw new RuntimeException("發文不存在");
		}
		postRepository.deleteById(postId);
	}
}
