package cn.omsfuk.blog.controller;

import cn.omsfuk.blog.base.Result;
import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.domain.Note;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.service.NoteFormatIOService;
import cn.omsfuk.blog.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Created by omsfuk on 17-5-5.
 */

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private HttpSession session;

    @Autowired
    private NoteFormatIOService noteFormatIOService;

    /**
     * --------增--------
     */

    /**
     * 上传文件，批量新增笔记
     * @param files
     * @param
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result insertNoteByFile(@RequestParam("file") CommonsMultipartFile[] files, @RequestParam("path") String path) {
        String error = "";

        User user = (User) session.getAttribute("user");

        for(CommonsMultipartFile file : files) {

            String content = null;
            try {
//                content = new String(file.getBytes(), "unicode");
                content = new String(file.getBytes(), "utf-8");
                System.out.println(content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                noteService.automaticInsertNote(path, content, user, noteFormatIOService);
            } catch (Exception e) {
                error += file.getOriginalFilename() + " 导入失败";
                error += e.getMessage();
                e.printStackTrace();
            }
        }

        if(!"".equals(error)) {
            return ResultCache.getFailure(error);
        } else {
            return ResultCache.OK;
        }

    }

    /**
     * 新增单条笔记
     * @param note
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result insertNote(@Valid Note note) {
        User user = (User) session.getAttribute("user");
        int num = noteService.insertNote(note, user);
        return ResultCache.getOK(num);
    }

    /**
     * 修改
     * @param note
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result updateNote(@Valid Note note) {
        User user = (User) session.getAttribute("user");
        Integer res = noteService.updateNote(note, user);

        return ResultCache.getOK(res);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public Result deleteNode(@PathVariable Integer id) {
        User user = (User) session.getAttribute("User");
        return ResultCache.getOK(noteService.deleteNote(id, user));
    }

    /**
     * --------查--------
     */

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public Result download(String path, HttpServletResponse response) {
        if (path == null) {
            return ResultCache.FAILURE;
        }
        User user = (User) session.getAttribute("user");
        noteService.download(path, response, user);
        return null;
    }

    @RequestMapping(value = "/all/tree", method = RequestMethod.GET)
    public Result getFileTree() {
        User user = (User) session.getAttribute("user");
        return ResultCache.getOK(noteService.getFileTree(user));
    }

    /**
     * 所有笔记
     * @param page
     * @return
     */
    @RequestMapping(value = "/all/list", method = RequestMethod.GET)
    public Result getAllNote(Integer page) {
        User user = (User) session.getAttribute("user");
        if(page == null) {
            page = 0;
        }
        return ResultCache.getOK(noteService.getAllNote(page, user));
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result getNote(@RequestParam(value = "tag", required = false) String tag,
                          @RequestParam(value = "id", required = false) Integer id,
                          @RequestParam(value = "url", required = false) String url,
                          @RequestParam(value = "page", required = false) Integer page) {
        User user = (User) session.getAttribute("user");
        return noteService.getNote(tag, url, id, page, 5, user);
    }

    /**
     * 根据标签查找笔记
     * @param tag
     * @return
     */
    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public Result getNoteByTag(@PathVariable String tag) {
        User user = (User) session.getAttribute("user");
        return ResultCache.getOK(noteService.getNoteByTag(tag, user));
    }

    /**
     * 根据url查找笔记
     * @param url
     * @return
     */
    @RequestMapping(value = "/url/{url}", method = RequestMethod.GET)
    public Result getNoteByUrl(@PathVariable String url) {
        User user = (User) session.getAttribute("user");

        return ResultCache.getOK(noteService.getNoteByUrl(url, user));
    }

    /**
     * 通过id获得note
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Result getNoteById(@PathVariable("id") Integer id) {
        User user = (User) session.getAttribute("user");
        return ResultCache.getOK(noteService.getNoteById(id, user));
    }

    /**
     * 笔记总数
     * @return
     */
    @RequestMapping(value = "/all/count", method = RequestMethod.GET)
    public Result getPageCount(Integer id) {
        User user = (User) session.getAttribute("user");
        return ResultCache.getOK(noteService.getNoteCount(user));
    }

}
