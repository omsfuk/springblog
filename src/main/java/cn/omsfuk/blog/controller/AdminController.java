package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.bean.MD5Factory;
import cn.omsfuk.blog.error.exception.NotFoundException;
import cn.omsfuk.blog.model.Picture;
import cn.omsfuk.blog.model.Post;
import cn.omsfuk.blog.model.Tag;
import cn.omsfuk.blog.model.User;
import cn.omsfuk.blog.service.PictureService;
import cn.omsfuk.blog.service.PostService;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by omsfuk on 17-4-23.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MD5Factory md5Factory;

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public String insertPost(@Valid Post post, Errors errors, Model model) {
        if (errors.hasErrors()) {
            String msg = "";
            List<ObjectError> ers = errors.getAllErrors();
            for (ObjectError err : ers) {
                msg += err;
            }
            model.addAttribute("cause", msg);
            return "error";
        }

        try {
            Integer res = postService.insertPost(post);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("cause", e.getMessage());
            return "error";
        }

        return "redirect:/admin/posts";
    }

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("postCount", postService.getPostsCount());
        return "admin/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost() {
        return "admin/new";
    }

    @RequestMapping(value = "/post/del", method = RequestMethod.POST)
    public String delPost(Integer id, Model model) {
        if (id == null) {
            model.addAttribute("cause", "文章id不能为空");
            return "error";
        }

        int res = postService.deletePost(id);
        if (res == 0) {
            model.addAttribute("cause", "指定id的文章不存在");
            return "error";
        }

        return "redirect:/admin/posts";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String posts(Integer page, Model model) {
        if (page == null) {
            page = 0;
        }

        if (page < 0) {
            throw new NotFoundException();
        }

        List<Post> posts = null;
        Integer totalPages = 0;

        try {
            posts = postService.getAllPosts(page);
            totalPages = postService.getPostsCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("totalPages", totalPages / 5 + (totalPages % 5 == 0 ? 0 : 1));
        model.addAttribute("page", page);

        return null;
    }

    @RequestMapping(value = "/post/update", method = RequestMethod.POST)
    public String updatePost(@Valid Post post, Errors errors, Model model) {
        if (errors.hasErrors() || post.getId() == null) {
            model.addAttribute("cause", "表单格式错误");
            return "error";
        }

        try {
            postService.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("cause", e.getMessage());
            return "error";
        }

        return "redirect:/admin/posts";
    }

    @RequestMapping(value = "/post/edit", method = RequestMethod.GET)
    public String edit(Integer id, Model model) {
        if (id == null || id < 0) {
            model.addAttribute("cause", "指定的id不存在");
            return "error";
        }
        if (!postService.isPostExist(id)) {
            model.addAttribute("cause", "指定id的文章不存在");
            return "error";
        }
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "admin/edit";
    }

    @RequestMapping(value = "/tags/update")
    public String updateTags(String old, @RequestParam("new") String nw, Model model) {
        if (old == null || "".equals(old) || nw == null || "".equals(nw)) {
            model.addAttribute("cause", "请求参数格式不正确");
            return "error";
        }

        tagService.updateTags(old, nw);
        return "redirect:/admin/tags";
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String tags(Model model) {
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);
        return "admin/tags";
    }

    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile multipartFile, Model model, HttpSession session) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("cause", "文件名不能为空");
            return "error";
        }

        if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".jpeg")
                && !multipartFile.getOriginalFilename().endsWith(".gif") && !multipartFile.getOriginalFilename().endsWith(".png")) {
            model.addAttribute("cause", "请上传图片文件");
            return "error";
        }

        String fileName = session.getServletContext().getRealPath("/upload");
        fileName += "/" + md5Factory.getMd5(new Date(System.currentTimeMillis()) + multipartFile.getOriginalFilename());
        fileName += multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("info", "文件名为" + file.getName());
        return "success";
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpSession session, Model model) {
        List<Picture> pictures = pictureService.getAllPictures(session.getServletContext().getRealPath("/upload"));
        System.out.println(pictures.size());
        model.addAttribute("pictures", pictures);
        return "admin/upload";
    }

    @RequestMapping(value = "/delResource", method = RequestMethod.POST)
    public String delResource(@RequestParam("name") String name, HttpSession session, Model model) {
        String fileName = session.getServletContext().getRealPath("/upload") + "/" + name;
        File file = new File(fileName);
        if(file.exists()) {
            if(file.delete()) {
                return "redirect:/admin/upload";
            } else {
                model.addAttribute("cause", "删除失败");
                return "error";
            }
        } else {
            model.addAttribute("cause", "文件不存在");
            return "error";
        }
    }
}
