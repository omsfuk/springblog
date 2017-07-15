package cn.omsfuk.blog.domain;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * Created by omsfuk on 17-4-22.
 */

@Data
public class Note {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String title;

    @Digits(integer = 11, fraction = 0)
    private Integer userid;

    private String username;

    private String tags = "";

    private String directory;

    @NotNull
    private Integer directoryid;

    private Date lastModify = new Date(System.currentTimeMillis());

    private int views = 0;

    @NotNull
    private String url;

    @NotNull
    @Size(max = 65535)
    private String content;

    private Note nextNote;

    private Note previousNote;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        if (!tags.startsWith(",")) {
            tags = "," + tags;
        }
        if (!tags.endsWith(",")) {
            tags = tags + ",";
        }
        this.tags = tags;
    }
}
