package cn.omsfuk.blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * Created by omsfuk on 17-4-22.
 */
public class Post {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String title;

    private String content;

    private String tags;

    @NotNull
    private String url;

    private Date tdate;

    private int vtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        if(!tags.startsWith(",")) {
            tags = "," + tags;
        }
        if(!tags.endsWith(",")) {
            tags = tags + ",";
        }
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public int getVtime() {
        return vtime;
    }

    public void setVtime(int vtime) {
        this.vtime = vtime;
    }

}
