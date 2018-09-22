package ${basepackage}.service;

public class ServiceException extends Exception {
    private int code;
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
