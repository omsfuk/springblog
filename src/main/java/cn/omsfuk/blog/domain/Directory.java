package cn.omsfuk.blog.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-5-5.
 */
public class Directory {

    private Integer id;

    private String path;

    private Integer userid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if(!path.endsWith("/")) {
            path += "/";
        }
        this.path = path;
    }
}
