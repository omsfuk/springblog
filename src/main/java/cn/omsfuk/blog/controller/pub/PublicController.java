package cn.omsfuk.blog.controller.pub;

import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.domain.User;
import cn.omsfuk.blog.base.Result;
import cn.omsfuk.blog.service.NoteService;
import cn.omsfuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows) {

        return noteService.getNote(tag, url, id, page, rows, new User(userid));
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Result getTags(@RequestParam("userid") Integer userid) {
        return tagService.getAllTags(userid);
    }

    @RequestMapping(value = "/notes/archive", method = RequestMethod.GET)
    public Result getNoteArchive(@RequestParam("userid") Integer userid) {
        return noteService.getNoteArchive(new User(userid));
    }

    @RequestMapping(value = "/notes/nearby", method = RequestMethod.GET)
    public Result getNote(Integer id, Integer userid) {
//        noteService.getNextNote(id, );
        return null;
    }

    @RequestMapping(value = "/notes/count", method = RequestMethod.GET)
    public Result getNotesCount(Integer userid) {
        User user = new User();
        user.setId(userid);
        return ResultCache.getOK(noteService.getNoteCount(user));
    }
}
