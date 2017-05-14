package cn.omsfuk.blog.dao;

import cn.omsfuk.blog.domain.Directory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-5-5.
 */

@Repository
public interface DirectoryDao {

    Integer insertDirectory(Directory directory);

    Integer deleteDirectory(Map<String, Object> map);

    List<Directory> getAllDirectory(Integer id);

    Integer updateDirectory(Map<String, Object> map);

    Directory getDirectoryByPath(Map<String, Object> map);
}
