package cn.omsfuk.blog.controller.restful;

import cn.omsfuk.blog.dao.DirectoryDao;
import cn.omsfuk.blog.domain.Note;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.dto.Result;
import cn.omsfuk.blog.service.NoteFormatIOService;
import cn.omsfuk.blog.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

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
        Result result = new Result();
        String error = "";

        User user = (User) session.getAttribute("user");

        for(CommonsMultipartFile file : files) {

            String content = null;
            try {
                content = new String(file.getBytes(), "unicode");
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
            result.setSuccess(false);
            result.setError(error);
        } else {
            result.setSuccess(true);
        }

        return result;
    }

    /**
     * 新增单条笔记
     * @param note
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result insertNote(@Valid Note note) {
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        int num = noteService.insertNote(note, user);
        result.setSuccess(true);
        result.setData(num);
        return result;
    }

    /**
     * 修改
     * @param note
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result updateNote(@Valid Note note) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        Integer res = noteService.updateNote(note, user);

        result.setSuccess(true);
        result.setData(res);
        return result;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public Result deleteNode(@PathVariable Integer id) {
        Result result = new Result();
        User user = (User) session.getAttribute("User");
        result.setData(noteService.deleteNote(id, user));
        result.setSuccess(true);
        return result;
    }

    /**
     * --------查--------
     */
    @RequestMapping(value = "/all/tree", method = RequestMethod.GET)
    public Result getFileTree() {
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        result.setData(true);
        result.setData(noteService.getFileTree(user));
        return result;
    }

    /**
     * 所有笔记
     * @param page
     * @return
     */
    @RequestMapping(value = "/all/list", method = RequestMethod.GET)
    public Result getAllNote(Integer page) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        if(page == null) {
            page = 0;
        }
        List<Note> notes = noteService.getAllNote(page, user);

        result.setSuccess(true);
        result.setData(notes);
        return result;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result getNote(@RequestParam(value = "tag", required = false) String tag,
                          @RequestParam(value = "id", required = false) Integer id,
                          @RequestParam(value = "url", required = false) String url,
                          @RequestParam(value = "page", required = false) Integer page) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        result.setData(noteService.getNote(tag, url, id, page, user));
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据标签查找笔记
     * @param tag
     * @return
     */
    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public Result getNoteByTag(@PathVariable String tag) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");

        result.setSuccess(true);
        result.setData(noteService.getNoteByTag(tag, user));
        return result;
    }

    /**
     * 根据url查找笔记
     * @param url
     * @return
     */
    @RequestMapping(value = "/url/{url}", method = RequestMethod.GET)
    public Result getNoteByUrl(@PathVariable String url) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        Note note = noteService.getNoteByUrl(url, user);

        result.setSuccess(true);
        result.setData(note);
        return result;
    }

    /**
     * 通过id获得note
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Result getNoteById(@PathVariable("id") Integer id) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        result.setSuccess(true);
        result.setData(noteService.getNoteById(id, user));
        return result;
    }

    /**
     * 笔记总数
     * @return
     */
    @RequestMapping(value = "/all/count", method = RequestMethod.GET)
    public Result getPageCount(Integer id) {
        Result result = new Result();
        User user = (User) session.getAttribute("user");

        result.setSuccess(true);
        result.setData(noteService.getNoteCount(user));
        return result;
    }

}
