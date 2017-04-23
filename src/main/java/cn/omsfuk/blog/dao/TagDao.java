package cn.omsfuk.blog.dao;

import cn.omsfuk.blog.model.Tag;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-4-23.
 */
public interface TagDao {

    List<Tag> getAllTags();

    Integer insertTag(Tag tag);

    Integer getTagByName(String name);

    Integer updateTag(Map<String, String> map);
}
