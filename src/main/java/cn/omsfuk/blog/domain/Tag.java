package cn.omsfuk.blog.domain;

/**
 * Created by omsfuk on 17-4-23.
 */
public class Tag {

    private Integer id;

    private String name;

    private Integer  userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
