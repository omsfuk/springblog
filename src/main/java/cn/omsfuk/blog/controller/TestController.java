package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.dao.RoleDao;
import cn.omsfuk.blog.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by omsfuk on 17-5-5.
 */

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private RoleDao roleDao;

    @RequestMapping(value = "/role/name/{name}", method = RequestMethod.GET)
    public Role getRole() {
        return roleDao.getRoleByName("Administrator");
    }

}
