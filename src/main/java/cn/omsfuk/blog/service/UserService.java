package cn.omsfuk.blog.service;

import cn.omsfuk.blog.dao.UserDao;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by omsfuk on 17-5-5.
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    
    public boolean userLogin(String name, String password, HttpSession session, HttpServletResponse response) {
        User user = userDao.getUserByName(name);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
