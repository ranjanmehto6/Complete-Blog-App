package com.demo.blog.repositry;


import com.demo.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositry extends JpaRepository<Comment,Long> {


    List<Comment> findByPostId(long postId);


}