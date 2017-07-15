package cn.omsfuk.blog.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by omsfuk on 17-4-26.
 */

@Data
@RequiredArgsConstructor
public class User {

    @NonNull
    private Integer id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(min = 5)
    private String password;

    @NotNull
    @Pattern(regexp = "/^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$/")
    private String email;

    private String role;

    public User() {

    }

}
