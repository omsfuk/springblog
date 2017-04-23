package cn.omsfuk.blog.service.impl;

import cn.omsfuk.blog.dao.TagDao;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by omsfuk on 17-4-23.
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

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
}
