package com.underhill.nick.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.underhill.nick.bulletinboard.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
