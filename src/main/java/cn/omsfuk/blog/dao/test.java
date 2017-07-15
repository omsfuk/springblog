package cn.omsfuk.blog.dao;

import org.apache.ibatis.annotations.Select;

/**
 * Created by omsfuk on 2017/7/14.
 */
public interface test {
    @Select("select content from note where id = 12")
    String get();
}
