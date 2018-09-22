package ${basepackage}.controller.response;

import ${basepackage}.service.Errors;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {
    private int code;
    private String message;
    private boolean success;
    private T data;

    public static CommonResponse successResponse() {
        CommonResponse response = new CommonResponse<>();
        response.setSuccess(true);
        response.setCode(0);
        return response;
    }

    public static <T> CommonResponse successResponse(T data) {
        CommonResponse commonResponse = successResponse();
        commonResponse.setData(data);
        return commonResponse;
    }

    public static CommonResponse errorResponse(int code, String message) {
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        return commonResponse;
    }

    public static CommonResponse errorResponse(Errors errors) {
        return errorResponse(errors.getCode(), errors.getMessage());
    }
}
