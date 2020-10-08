package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.Post;
import org.springframework.data.domain.Page;

public interface PostService {

    enum SORT {
        ASC, DESC
    }

    void save(Post post, String author);

    Page<Post> getOrderedPosts(PostService.SORT sort, int page, int size);
}
