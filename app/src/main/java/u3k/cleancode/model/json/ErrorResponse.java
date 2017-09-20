package u3k.cleancode.model.json;

import u3k.cleancode.utils.Utils;

/**
 * Created by Vladimir Skoupy.
 */

public class ErrorResponse extends BaseResponse {

    private String error;

    private String message;


    public static ErrorResponse create(String error) {
        return create(error, null);
    }

    public static ErrorResponse create(String error, String message) {
        ErrorResponse item = new ErrorResponse();
        item.setError(error);
        item.setMessage(message);
        return item;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEmpty() {
        return Utils.isEmpty(error);
    }
}
