package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.PostRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(Post post, String author) {
        User user = userRepository.getUserByEmail(author);
        if (user != null) {
            post.setAuthor(user);
        }
        post.setWhenAdd(LocalDateTime.now());
        postRepository.save(post);
    }

    @Transactional
    public Page<Post> getOrderedPosts(SORT sort, int page, int size) {
        if (sort == SORT.ASC) {
            return postRepository.findAll(PageRequest.of(page, size, Sort.by("whenAdd")));
        } else {
            return postRepository.findAll(PageRequest.of(page, size, Sort.by("whenAdd").descending()));
        }
    }

    public enum SORT {
        ASC, DESC
    }
}


