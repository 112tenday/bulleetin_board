package com.iwan.bulletinboard.mapper;

import com.iwan.bulletinboard.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    List<Post> findAll();
    Post findById(Long id);
    void save(Post post);
    void update(Post post);
    void deleteById(Long id);
    Post getPostById(Long id);
    void incrementViews(Long id);
}

