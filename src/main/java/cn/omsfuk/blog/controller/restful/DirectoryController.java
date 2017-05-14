package cn.omsfuk.blog.controller.restful;

import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.dto.Result;
import cn.omsfuk.blog.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by omsfuk on 17-5-7.
 */

@RestController
@RequestMapping("/api/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result insertDirectory(@RequestParam("path") String path) {
        User user = (User) session.getAttribute("user");
        Result result = new Result();

        result.setData(directoryService.insertDirectory(path, user));

        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public Result deleteDirectory(@PathVariable Integer id) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        result.setData(directoryService.deleteDirectory(id, user));
        result.setSuccess(true);
        return result;
    }


}
