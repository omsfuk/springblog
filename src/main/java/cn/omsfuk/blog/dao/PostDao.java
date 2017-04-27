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

    Integer getPostsCount();

    List<Post> getAllPosts(Integer page);

    Post getPostById(Integer id);

    Post getPostByUrl(String url);

    List<Post> getPostsByTag(String tag);

    Integer insertPost(Post post);

    Integer updatePost(Post post);

    Integer deletePost(Integer id);

    int incVtime(Integer id);

    Integer updateTags(Map<String, String> map);

    Post getNextPost(Integer id);

    Post getPreviousPost(Integer id);

}
