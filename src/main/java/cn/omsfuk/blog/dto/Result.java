package cn.omsfuk.blog.dto;

/**
 * Created by omsfuk on 17-4-22.
 */
public class Result {

    private boolean success = false;

    public Object data;

    public String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
