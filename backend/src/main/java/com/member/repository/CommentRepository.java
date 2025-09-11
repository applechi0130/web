package com.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.member.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByPost_PostIdOrderByCreatedAtAsc(Integer postId);
}
