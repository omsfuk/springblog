package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.model.Post;
import cn.omsfuk.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by omsfuk on 17-4-23.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public String insertPost(@Valid Post post, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("cause", "表单格式错误");
            return "error";
        }

        System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\r\n");

        try {
            Integer res = postService.insertPost(post);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("cause", e.getMessage());
            return "error";
        }

        return "success";
    }
}
