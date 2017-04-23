package cn.omsfuk.blog.error;

import cn.omsfuk.blog.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by omsfuk on 17-4-23.
 */

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(NotFoundException exception, Model model) {
        model.addAttribute("cause", exception.getMessage());
        return "error";
    }

}
