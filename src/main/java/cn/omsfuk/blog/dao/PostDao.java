package cn.omsfuk.blog.dao;

/**
 * Created by omsfuk on 17-4-22.
 */

import cn.omsfuk.blog.model.Post;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-4-16.
 */
public interface PostDao {

    public Integer getPostsCount();

    public List<Post> getAllPosts(Integer page);

    public Post getPostById(Integer id);

    public Post getPostByUrl(String url);

    public List<Post> getPostsByTag(String tag);

    public Integer insertPost(Post post);

    public Integer updatePost(Post post);

    public Integer deletePost(Integer id);

    public int incVtime(Integer id);

    public Integer updateTags(Map<String, String> map);

}
