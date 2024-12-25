package com.iwan.bulletinboard.service;

import com.iwan.bulletinboard.model.Post;

import java.util.List;

public interface PostService {
    List<Post>getAllPosts();
    Post getPostById(Long id);

    Post getPostByIdView(Long id);

    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(Long id, String password);
}
