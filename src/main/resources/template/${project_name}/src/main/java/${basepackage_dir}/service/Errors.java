package ${basepackage}.service;

import lombok.Getter;

public enum Errors {
    _500(500, "Server error.");

    @Getter
    private int code;
    @Getter
    private String message;

    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
