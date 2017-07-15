package cn.omsfuk.blog.dao;

/**
 * Created by omsfuk on 17-4-22.
 */

import cn.omsfuk.blog.domain.Note;
import cn.omsfuk.blog.domain.NoteArchive;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-4-16.
 */

@Repository
public interface NoteDao {

    //查
    Integer getNoteCount(Integer userid);

    List<Note> getNote(Map<String, Object> map);

    Note getNoteById(Integer id);

    Note getNoteByUrl(Map<String, Object> map);

    Note getNextNote(Map<String, Object> map);

    Note getPreviousNote(Map<String, Object> map);

    List<Note> getAllNote(Map<String, Object> map);

    List<Note> getNoteByDirectoryId(Map<String, Object> map);

    List<Note> getNoteByTag(Map<String, Object> map);

    List<NoteArchive> getNoteArchiveByDate(Integer userid);

    //增
    Integer insertNote(Note note);

    //改
    Integer updateNote(Note note);

    Integer updateTags(Map<String, String> map);

    int incViews(Integer id);

    //删
    Integer deleteNote(Integer id);

}
