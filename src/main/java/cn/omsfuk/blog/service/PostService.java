package cn.omsfuk.blog.service;

import cn.omsfuk.blog.dao.PostDao;
import cn.omsfuk.blog.model.Post;

import java.sql.Date;
import java.util.List;

/**
 * Created by omsfuk on 17-4-22.
 */
public interface PostService {

    Post getPostById(Integer id);

    int insertPost(Post post);

    int deletePost(Integer id);

    boolean isPostExist(Integer id);

    Post getPostByUrl(String url);

    List<Post> getAllPosts(int page);

    List<Post> getPostsByTag(String tag);

    int getPostsCount();

    int updatePost(Post post);

    Post getNextPost(Integer id);

    Post getPreviousPost(Integer id);

}
