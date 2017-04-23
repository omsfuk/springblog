package cn.omsfuk.blog.service;

import cn.omsfuk.blog.dao.PostDao;
import cn.omsfuk.blog.model.Post;

import java.sql.Date;
import java.util.List;

/**
 * Created by omsfuk on 17-4-22.
 */
public interface PostService {

    public Post getPostById(Integer id);

    public int insertPost(Post post);

    public int deletePost(Integer id);

    public boolean isPostExist(Integer id);

    public Post getPostByUrl(String url);

    public List<Post> getAllPosts(int page);

    public List<Post> getPostsByTag(String tag);

    public int getPostsCount();

    public int updatePost(Post post);

}
