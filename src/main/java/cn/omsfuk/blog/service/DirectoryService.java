package cn.omsfuk.blog.service;

import cn.omsfuk.blog.dao.DirectoryDao;
import cn.omsfuk.blog.domain.Directory;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by omsfuk on 17-5-7.
 */
@Service
public class DirectoryService {

    @Autowired
    private DirectoryDao directoryDao;

    
    public Integer insertDirectory(String path, User user) {
        Directory directory = new Directory();
        directory.setUserid(user.getId());
        directory.setPath(path);

        String parentPath = directory.getPath().substring(0, directory.getPath().lastIndexOf('/', directory.getPath().length() - 2) + 1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        map.put("path", parentPath);

        if(directoryDao.getDirectoryByPath(map) != null) {
            directoryDao.insertDirectory(directory);
        }

        return directory.getId();
    }

    
    public boolean deleteDirectory(Integer id, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        map.put("id", id);

        System.out.println(directoryDao.deleteDirectory(map));
        return true;
    }
}
