package com.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.member.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	// 取得最新發文清單
	List<Post> findAllByOrderByCreatedAtDesc();
}
