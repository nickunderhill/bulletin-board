package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.PostRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
    public List<Post> getOrderedPosts(SORT sort) {
        List<Post> orderedList = postRepository.findAll();
        if (sort == SORT.ASC) {
            orderedList.sort(Comparator.comparing(Post::getWhenAdd));
        } else {
            orderedList.sort(Comparator.comparing(Post::getWhenAdd).reversed());
        }
        return orderedList;
    }

    public enum SORT {
        ASC, DESC
    }
}


