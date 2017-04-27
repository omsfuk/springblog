package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.error.exception.NotFoundException;
import cn.omsfuk.blog.model.Post;
import cn.omsfuk.blog.model.Result;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.model.User;
import cn.omsfuk.blog.service.PostService;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
        Integer totalPages = 0;

        try {
            posts = postService.getAllPosts(page);
            tags = tagService.getAllTags();
            totalPages = postService.getPostsCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        model.addAttribute("totalPages", totalPages / 5 + (totalPages % 5 == 0 ? 0 : 1));
        model.addAttribute("page", page);

        return "home";
    }

    @RequestMapping(value = "/post/{url}")
    public String getPostByUrl(@PathVariable String url, Model model) {
        List<Tag> tags = null;
        Post post = null;
        Post nextPost = null;
        Post previousPost = null;

        try {
            post = postService.getPostByUrl(url);
            tags = tagService.getAllTags();
            nextPost = postService.getNextPost(post.getId());
            previousPost = postService.getPreviousPost(post.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("tags", tags);
        model.addAttribute("url", url);
        model.addAttribute("post", post);
        model.addAttribute("nextPost", nextPost);
        model.addAttribute("previousPost", previousPost);

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

    @RequestMapping(value = "/getPost/{url}", method = RequestMethod.GET)
    @ResponseBody
    public Result post(@PathVariable String url) {
        Result result = new Result();
        Post post = null;
        try {
            post = postService.getPostByUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("获取失败");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(post);
        return result;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    public String userLogin(@Valid User user, HttpSession session) {
        // MDZZ，硬编码，，，，
        if("omsfuk".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            session.setAttribute("login", true);
            session.setAttribute("username", user.getUsername());
        }
        return "redirect:/admin/index";
    }

}
