package cn.omsfuk.blog.dao;

/**
 * Created by omsfuk on 17-4-22.
 */

import cn.omsfuk.blog.model.Post;

import java.util.List;

/**
 * Created by omsfuk on 17-4-16.
 */
public interface PostDao {

    public List<Post> getAllPosts(Integer page);

    public Post getPostById(Integer id);

    public Post getPostByUrl(String url);

    public List<Post> getPostsByTag(String tag);

    public int insertPost(Post post);

    public int updatePost(Post post);

    public int deletePost(Integer id);

    public int incVtime(Integer id);
}
