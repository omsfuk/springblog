package cn.omsfuk.blog.base;

/**
 * Created by omsfuk on 17-5-14.
 */
public class ResultCache {

    public static final Result OK = new Result(ResultStatusValues.V_OK, ResultStatusMessage.M_OK);

    public static final Result FAILURE = new Result(ResultStatusValues.V_FAILURE, ResultStatusMessage.M_FAILURE);

    public static final Result ERMISSION_DENIED = new Result(ResultStatusValues.V_PERMISSION_DENIED, ResultStatusMessage.M_PERMISSION_DENIED);

    public static Result get(Integer type) {
        if (type == 1) {
            return new Result(ResultStatusValues.V_OK, ResultStatusMessage.M_OK);
        } else if (type == 2) {
            return new Result(ResultStatusValues.V_FAILURE, ResultStatusMessage.M_FAILURE);
        } else {
            return new Result(ResultStatusValues.V_PERMISSION_DENIED, ResultStatusMessage.M_PERMISSION_DENIED);
        }
    }

    public static Result getOK(Object obj) {
        Result result = new Result(ResultStatusValues.V_OK, ResultStatusMessage.M_OK);
        result.setData(obj);
        return result;
    }

    public static Result getFailure(Object obj) {
        Result result = new Result(ResultStatusValues.V_FAILURE, ResultStatusMessage.M_FAILURE);
        result.setData(obj);
        return result;
    }

}
