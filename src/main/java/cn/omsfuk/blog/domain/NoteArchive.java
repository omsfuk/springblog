package cn.omsfuk.blog.domain;

import java.sql.Date;
import java.util.List;

/**
 * Created by omsfuk on 17-5-9.
 */
public class NoteArchive {

    private Date date;

    private List<Note> notes;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
