package cn.omsfuk.blog.controller.restful.pub;

import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.dto.Result;
import cn.omsfuk.blog.service.NoteFormatIOService;
import cn.omsfuk.blog.service.NoteService;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Created by omsfuk on 17-5-9.
 */

@RestController
@RequestMapping(value = "/api/pub", method = RequestMethod.GET)
public class PublicController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/notes")
    public Result getNote(
            @RequestParam(value = "userid", required = true) Integer userid,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "page", required = false) Integer page) {
        Result result = new Result();

        User user = new User();
        user.setId(userid);
        result.setData(noteService.getNote(tag, url, id, page, user));
        result.setSuccess(true);
        return result;
    }

    /**
     * 笔记总数
     * @return
     */
    @RequestMapping(value = "/notes/count", method = RequestMethod.GET)
    public Result getPageCount(@RequestParam(value = "userid", required = true) Integer userid) {
        Result result = new Result();

        User user = new User();
        user.setId(userid);
        result.setData(noteService.getNoteCount(user));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Result getTags(@RequestParam("userid") Integer userid) {
        Result result = new Result();
        User user = new User();
        user.setId(userid);
        result.setData(tagService.getAllTags(user));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/notes/archive", method = RequestMethod.GET)
    public Result getNoteArchive(@RequestParam("userid") Integer userid) {
        Result result = new Result();
        User user = new User();
        user.setId(userid);
        result.setData(noteService.getNoteArchive(user));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/notes/next", method = RequestMethod.GET)
    public Result getNote(Integer id, Integer userid) {
//        noteService.getNextNote(id, );
        return null;
    }
}
