package cn.omsfuk.blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by omsfuk on 17-4-26.
 */
public class User {

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(min = 5)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
