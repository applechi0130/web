package com.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.member.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findAllByOrderByCreatedAtDesc();
}
