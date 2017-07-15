package cn.omsfuk.blog.service;

import cn.omsfuk.blog.base.Result;
import cn.omsfuk.blog.base.ResultCache;
import cn.omsfuk.blog.dao.DirectoryDao;
import cn.omsfuk.blog.dao.NoteDao;
import cn.omsfuk.blog.dao.TagDao;
import cn.omsfuk.blog.domain.*;
import cn.omsfuk.blog.error.exception.NotFoundException;
import cn.omsfuk.blog.vo.NotesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by omsfuk on 17-4-16.
 */

@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    DirectoryDao directoryDao;

    
    public int insertNote(Note note, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        note.setUserid(user.getId());

        ensureTagExist(note, user);

        return noteDao.insertNote(note);
    }

    private void ensureTagExist(Note note, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        if(note.getTags() != null) {
            String[] tags = note.getTags().split(",");
            for (String tag : tags) {
                if(tag != null & !"".equals(tag)) {
                    map.put("name", tag);
                    if(tagDao.getTagByName(map) == null) {
                        Tag tagDo = new Tag();
                        tagDo.setUserid(user.getId());
                        tagDo.setName(tag);
                        tagDao.insertTag(tagDo);
                    }
                }
            }
        }
    }
    
    public boolean automaticInsertNote(String path, String content, User user, NoteFormatIOService noteFormatIOService) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", path);
        map.put("userid", user.getId());

        Note note = noteFormatIOService.resolveRawNote(content);
        if (note == null) {
            throw new RuntimeException("转换失败，格式错误");
        }

        ensureTagExist(note, user);

        note.setDirectoryid(directoryDao.getDirectoryByPath(map).getId());
        note.setUserid(user.getId());
        noteDao.insertNote(note);
        return true;
    }

    public Result getNoteArchive(User user) {
        return ResultCache.getOK(noteDao.getNoteArchiveByDate(user.getId()));
    }

    
    public int deleteNote(Integer id, User user) {
        int res = noteDao.deleteNote(id);
        if(res == 0) {
            throw new NotFoundException();
        }

        return res;
    }

    
    public int updateNote(Note note, User user) {
        if(noteDao.getNoteById(note.getId()) == null) {
            throw new NotFoundException();
        }

        ensureTagExist(note, user);

        Map<String, Object> map = new HashMap<>();
        map.put("userid", user.getId());
        map.put("path", note.getDirectory());
        Directory directory = directoryDao.getDirectoryByPath(map);
        note.setDirectoryid(directory.getId());

        return noteDao.updateNote(note);
    }

    public void download(String path, HttpServletResponse response, User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", user.getId());
        map.put("limit", -1);
        List<Note> notes = noteDao.getAllNote(map);
        ZipOutputStream outputStream = null;
        try {
            outputStream = new ZipOutputStream(response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ZipOutputStream finalZipStream = outputStream;
        notes.stream()
                .filter(note -> note.getDirectory().startsWith(path))
                .forEach(note -> {
                    try {
                        String entryName = note.getDirectory() + note.getTitle() + ".md";
                        System.out.println(entryName);
                        System.out.println(note.getContent().length());
                        finalZipStream.putNextEntry(new ZipEntry((entryName.substring(1, entryName.length()))));
                        byte[] utf8Space = new byte[] { (byte) 194, (byte)160 };
                        String space = new String(utf8Space, "UTF-8");
                        finalZipStream.write(note.getContent().replace(space, " ").getBytes("UTF-8"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public AbstractFile getFileTree(User user) {
        List<Directory> directories = directoryDao.getAllDirectory(user.getId());
        AbstractFile fileTree = new AbstractFile();
        fileTree.setName("/");
        fileTree.setId(directories.get(0).getId());
        fileTree.setDirectory(true);

        for(Directory directory : directories) {
            AbstractFile nowFile = fileTree;
            for(String s : directory.getPath().split("/")) {
                if(s != null && s.length() != 0) {
                    AbstractFile abstractFile = nowFile.ensureChild(s + "/");
                    abstractFile.setDirectory(true);
                    abstractFile.setParentPath(nowFile.getParentPath() + nowFile.getName());

                    nowFile = abstractFile;
                }
            }
            nowFile.setId(directory.getId());
        }

        attachDocument(user.getId(), fileTree);

        return fileTree;
    }

    private void attachDocument(Integer userid, AbstractFile parentFile) {
        Map<String, Object> map = new HashMap<String, Object>();

        for (AbstractFile file : parentFile.getChildFiles()) {
            attachDocument(userid, file);
        }

        map.put("userid", userid);
        map.put("directoryid", parentFile.getId());
        List<Note> notes = noteDao.getNoteByDirectoryId(map);
        for (Note note : notes) {
            AbstractFile file = new AbstractFile();
            file.setName(note.getTitle());
            file.setDirectory(false);
            file.setParentPath(parentFile.getParentPath() + parentFile.getName());
            file.setId(note.getId());
            parentFile.addChild(file);
        }
    }

    
    public int getNoteCount(User user) {
        Integer res = noteDao.getNoteCount(user.getId());
        if(res == null) {
            res = 0;
        }
        return res;
    }

    
    public Result getNote(String tag, String url, Integer id, Integer page, Integer rows, User user) {
        List<Note> notes = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if(tag == null) {
            tag = "";
        }
        map.put("tag", tag);
        if(url == null) {
            url = "%";
        }
        map.put("url", url);
        if(id == null) {
            map.put("id", "%");
        } else {
            map.put("id", id);
        }
        if(rows == null) {
            rows = 5;
        }
        map.put("rows", 5);
        if(page == null) {
            page = 0;
        }
        map.put("page", page * rows);

        map.put("userid", user.getId());


        notes = noteDao.getNote(map);

        if((!"%".equals(url)) || id != null) {
            Map<String, Object> map0 = new HashMap<>();
            map0.put("userid", user.getId());
            map0.put("id", notes.get(0).getId());
            notes.get(0).setNextNote(noteDao.getNextNote(map0));
            notes.get(0).setPreviousNote(noteDao.getPreviousNote(map0));
            noteDao.incViews(notes.get(0).getId());
            notes.get(0).setViews(notes.get(0).getViews() + 1);
        }

        return ResultCache.getOK(new NotesVO(noteDao.getNoteCount(user.getId()), notes));
    }

    
    public Note getNoteByUrl(String url, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", url);
        map.put("userid", user.getId());
        Note note = noteDao.getNoteByUrl(map);
        noteDao.incViews(note.getId());
        note.setViews(note.getViews() + 1);
        return note;
    }

    
    public List<Note> getNoteByTag(String tag, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tag", tag);
        map.put("userid", user.getId());
        return noteDao.getNoteByTag(map);
    }

    
    public List<Note> getAllNote(int page, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page * 5);
        map.put("userid", user.getId());
        return noteDao.getAllNote(map);
    }

    
    public Note getNoteById(Integer id, User user) {
        return noteDao.getNoteById(id);
    }

}
