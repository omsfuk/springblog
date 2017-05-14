package cn.omsfuk.blog.vo;

import cn.omsfuk.blog.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by omsfuk on 17-5-14.
 */

@Data
@AllArgsConstructor
public class NotesVO {

    private Integer total;

    private List<Note> notes;
}
