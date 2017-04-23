package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.error.exception.NotFoundException;
import cn.omsfuk.blog.model.Post;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.service.PostService;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by omsfuk on 17-4-22.
 */

@Controller
@RequestMapping(method = RequestMethod.GET)
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = {"/home", "/"})
    public String home(Integer page, Model model) {

        if(page == null) {
            page = 0;
        }

        if(page < 0) {
            throw new NotFoundException();
        }

        List<Post> posts = null;
        List<Tag> tags = null;

        try {
            posts = postService.getAllPosts(page);
            tags = tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);

        return "home";
    }

    @RequestMapping(value = "/post/{url}")
    public String getPostByUrl(@PathVariable String url, Model model) {
        List<Tag> tags = null;
        Post post = null;

        try {
            post = postService.getPostByUrl(url);
            tags = tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("tags", tags);
        model.addAttribute("post", post);

        return "detail";
    }

    @RequestMapping(value = "/tag/{tagName}")
    public String getPostByTag(@PathVariable String tagName, Model model) {
        List<Tag> tags = null;
        List<Post> posts = null;

        try {
            posts = postService.getPostsByTag(tagName);
            tags = tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        System.out.println(posts.size());

        model.addAttribute("tags", tags);
        model.addAttribute("posts", posts);

        return "home";
    }

    @RequestMapping(value = "/archive")
    public String archive(Integer page, Model model) {

        if(page == null) {
            page = 0;
        }

        if(page < 0) {
            throw new NotFoundException();
        }

        List<Post> posts = null;
        List<Tag> tags = null;

        try {
            posts = postService.getAllPosts(page);
            tags = tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);

        return "archive";
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        List<Tag> tags = null;
        try {
            tags = tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
        model.addAttribute("tags", tags);
        return "about";
    }

}
