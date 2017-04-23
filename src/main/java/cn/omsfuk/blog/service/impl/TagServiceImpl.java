package cn.omsfuk.blog.service.impl;

import cn.omsfuk.blog.dao.PostDao;
import cn.omsfuk.blog.dao.TagDao;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-4-23.
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private PostDao postDao;

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public Integer insertTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    @Override
    public Integer getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public Integer updateTags(String old, String nw) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("old", old);
        map.put("new", nw);
        tagDao.updateTag(map);
        return postDao.updateTags(map);
    }

}
