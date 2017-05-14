package cn.omsfuk.blog.dao;

import cn.omsfuk.blog.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by omsfuk on 17-5-5.
 */
@Repository
public interface UserDao {

    Integer insertUser(User user);

    User getUserByName(String name);
}
