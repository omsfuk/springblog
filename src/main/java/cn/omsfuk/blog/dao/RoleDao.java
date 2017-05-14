package cn.omsfuk.blog.dao;

import cn.omsfuk.blog.domain.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by omsfuk on 17-5-5.
 */

@Repository
public interface RoleDao {

    Role getRoleByName(String roleName);

}
