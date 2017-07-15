package cn.omsfuk.blog.base;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by omsfuk on 17-4-22.
 */

@Data
@RequiredArgsConstructor
public class Result {

    @NonNull
    private Integer status;

    @NonNull
    private String message;

    private Object data;

}
