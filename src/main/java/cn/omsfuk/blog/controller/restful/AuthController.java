package cn.omsfuk.blog.controller.restful;

import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.dto.Result;
import cn.omsfuk.blog.service.UserService;
import org.apache.ibatis.executor.ReuseExecutor;
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
        Result result = new Result();
        if(userService.userLogin(username, password, session, response)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpSession session) {
        Result result = new Result();
        session.invalidate();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/jsessionid", method = RequestMethod.GET)
    public Result getSessionid(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        if(user != null) {
            result.setData(request.getRequestedSessionId());
            result.setSuccess(true);
        }

        return result;
    }

}
