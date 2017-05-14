package cn.omsfuk.blog.service;

import cn.omsfuk.blog.dao.NoteDao;
import cn.omsfuk.blog.dao.TagDao;
import cn.omsfuk.blog.domain.Tag;
import cn.omsfuk.blog.domain.User;
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
public class TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private NoteDao noteDao;

    
    public List<Tag> getAllTags(User user) {
        return tagDao.getAllTags(user.getId());
    }

    
    public Integer insertTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    
    public Integer getTagByName(String name, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        map.put("name", name);
        return tagDao.getTagByName(map);
    }

    
    public Integer updateTags(String old, String nw) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("old", old);
        map.put("new", nw);
        tagDao.updateTag(map);
        return noteDao.updateTags(map);
    }

}
