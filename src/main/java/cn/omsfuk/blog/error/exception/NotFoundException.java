package cn.omsfuk.blog.error.exception;

/**
 * Created by omsfuk on 17-4-23.
 */
public class NotFoundException extends RuntimeException {


    @Override
    public String getMessage() {
        return "页面未找到";
    }

}
