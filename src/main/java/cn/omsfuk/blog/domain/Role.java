package cn.omsfuk.blog.domain;

import java.util.List;

/**
 * Created by omsfuk on 17-5-5.
 */
public class Role {

    private Integer id;

    private String name;

    private List<String> privileges;

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

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    public Boolean hasPrivilege(String privilegeName) {
        if(privilegeName == null || privilegeName == "" || privilegeName.length() == 0) {
            return false;
        }
        // 改进效率
        for (String privilege : privileges) {
            if(privilegeName.equals(privilege)) {
                return true;
            }
        }

        return false;
    }
}
