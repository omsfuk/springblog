package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.base.Result;
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
        return ResultCache.getOK(directoryService.insertDirectory(path, user));
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public Result deleteDirectory(@PathVariable Integer id) {
        User user = (User) session.getAttribute("user");
        return ResultCache.getOK(directoryService.deleteDirectory(id, user));
    }


}
