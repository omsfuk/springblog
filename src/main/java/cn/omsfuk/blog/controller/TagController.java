package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.base.Result;
import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.domain.Tag;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by omsfuk on 17-5-5.
 */

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result getAllTags(Integer userid) {
        return ResultCache.getOK(tagService.getAllTags(userid));
    }
}
