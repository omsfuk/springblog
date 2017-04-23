package cn.omsfuk.blog.service.impl;

import cn.omsfuk.blog.dao.PostDao;
import cn.omsfuk.blog.dao.TagDao;
import cn.omsfuk.blog.error.exception.NotFoundException;
import cn.omsfuk.blog.model.Post;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by omsfuk on 17-4-16.
 */

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private TagDao tagDao;

    @Override
    public int insertPost(Post post) {

        if(post.getTags() != null) {
            String[] tags = post.getTags().split(",");
            for (String tag : tags) {
                if(tag != null & !"".equals(tag)) {
                    if(tagDao.getTagByName(tag) == null) {
                        tagDao.insertTag(new Tag(tag));
                    }
                }
            }
        }

        post.setTdate(new Date(System.currentTimeMillis()));

        return postDao.insertPost(post);
    }

    @Override
    public int deletePost(Integer id) {
        int res = postDao.deletePost(id);
        if(res == 0) {
            throw new NotFoundException();
        }

        return res;
    }

    @Override
    public boolean isPostExist(Integer id) {
        if(postDao.getPostById(id) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Post getPostByUrl(String url) {
        Post post = postDao.getPostByUrl(url);
        postDao.incVtime(post.getId());
        post.setVtime(post.getVtime() + 1);
        return post;
    }

    @Override
    public List<Post> getAllPosts(int page) {
        return postDao.getAllPosts(page * 10);
    }

    @Override
    public List<Post> getPostsByTag(String tag) {
        return postDao.getPostsByTag(tag);
    }

    @Override
    public int getPostsCount() {
        Integer res = postDao.getPostsCount();
        if(res == null) {
            res = 0;
        }
        return res;
    }

    @Override
    public int updatePost(Post post) {
        if(postDao.getPostById(post.getId()) == null) {
            throw new NotFoundException();
        }
        post.setTdate(new Date(System.currentTimeMillis()));
        return postDao.updatePost(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return postDao.getPostById(id);
    }

}
