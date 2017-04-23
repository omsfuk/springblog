package cn.omsfuk.blog.model;

/**
 * Created by omsfuk on 17-4-23.
 */
public class Tag {

    private Integer id;

    private String name;

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
}
