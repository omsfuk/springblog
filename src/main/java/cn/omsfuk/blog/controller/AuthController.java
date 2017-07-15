package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.base.Result;
import cn.omsfuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by omsfuk on 17-5-5.
 */

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, HttpServletResponse response) {
        if(userService.userLogin(username, password, session, response)) {
            return ResultCache.OK;
        } else {
            return ResultCache.FAILURE;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpSession session) {
        session.invalidate();
        return ResultCache.OK;
    }

    @RequestMapping(value = "/jsessionid", method = RequestMethod.GET)
    public Result getSessionid(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            return ResultCache.getOK(request.getRequestedSessionId());
        }
        return ResultCache.FAILURE;
    }

}
