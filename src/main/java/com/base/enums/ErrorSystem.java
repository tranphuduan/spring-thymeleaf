package com.base.enums;

public enum ErrorSystem {
    // Mã lỗi chung
    SUCCESS("00", "Successful."),
    SYSTEM_ERROR("01", "Lỗi hệ thống"),
    UNAUTHORIZED("02", "Thông tin xác thực không đúng"),
    FORBIDDEN("03", "Không có quyền truy cập"),

    // Lỗi liên quan DB (từ 100->199)
    DATA_NOT_EXIST("100","Không tồn tại trong DB"),
    DATA_EXIST("101","Dữ liệu đã tồn tại"),

    // Lỗi validate (406->499)
    INPUT_INCORRECT("406","Dữ liệu đầu vào không đúng"),
    PASS_NOT_FORMAT("407","Password không đúng định dạng"),
    DATE_NOT_FORMAT("408","Ngày tháng không đúng định dạng"),
    EMAIL_NOT_FORMAT("409","Email không đúng định dạng"),
    TOKEN_INCORRECT("410","Token không đúng"),
    ;

    public final String code;
    public final String mess;

    ErrorSystem(String code, String errorMessage) {
        this.code = code;
        this.mess = errorMessage;
    }
    public static ErrorSystem getByCode(String code) {
        for (ErrorSystem error : ErrorSystem.values()) {
            if (error.code().equals(code)) {
                return error;
            }
        }
        return null;
    }

    public String code() {
        return code;
    }

    public String mess() {
        return mess;
    }
}
