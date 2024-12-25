package com.iwan.bulletinboard.serviceImpl;

import com.iwan.bulletinboard.model.Post;
import com.iwan.bulletinboard.mapper.PostMapper;
import com.iwan.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> getAllPosts() {
        return postMapper.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Post post = postMapper.findById(id);

        return post;
    }
    @Override
    public Post getPostByIdView(Long id) {
        Post post = postMapper.findById(id);
        if (post != null) {
            postMapper.incrementViews(id);
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
        postMapper.save(post);
    }

    @Override
    public void updatePost(Post post) {
        Post existingPost = postMapper.getPostById(post.getId());
        if (post.getPassword() == null || post.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (!existingPost.getPassword().equals(post.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        postMapper.update(post);
    }

    @Override
    public void deletePost(Long id, String password) {
        Post existingPost = postMapper.getPostById(id);

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (!existingPost.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password.");
        }

        postMapper.deleteById(id);
    }



}

