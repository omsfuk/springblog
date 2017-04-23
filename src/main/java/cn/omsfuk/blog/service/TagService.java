package cn.omsfuk.blog.service;

import cn.omsfuk.blog.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by omsfuk on 17-4-23.
 */

public interface TagService {

    List<Tag> getAllTags();

    Integer insertTag(Tag tag);

    Integer getTagByName(String name);
}
