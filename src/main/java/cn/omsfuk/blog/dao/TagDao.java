package cn.omsfuk.blog.dao;

import cn.omsfuk.blog.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-4-23.
 */

@Repository
public interface TagDao {

    List<Tag> getAllTags(Integer userid);

    Integer insertTag(Tag tag);

    Integer getTagByName(Map<String, Object> map);

    Integer updateTag(Map<String, String> map);
}
