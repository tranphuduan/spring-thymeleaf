package com.example.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import enums.ErrorSystem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BaseResponse<T> {
    private String code;
    private String mess;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeResponse;
    @JsonIgnore
    private boolean success;

    public BaseResponse(ErrorSystem gatewayError) {
        this.code = gatewayError.code();
        this.mess = gatewayError.mess();
        this.success = gatewayError.code().equals(ErrorSystem.SUCCESS.code);
        this.timeResponse = new Date();
    }


    public BaseResponse(ErrorSystem errorSystem, T data) {
        this.code = errorSystem.code;
        this.mess = errorSystem.mess();
        this.data = data;
        this.success = errorSystem.code().equals(ErrorSystem.SUCCESS.code);
        this.timeResponse = new Date();
    }

    public BaseResponse(T data, String code, String mess) {
        this.data = data;
        this.code = code;
        this.mess = mess;
        this.success = code.equals(ErrorSystem.SUCCESS.code);
        this.timeResponse = new Date();
    }

    public BaseResponse(String code, String mess) {
        this.code = code;
        this.mess = mess;
        this.success = code.equals(ErrorSystem.SUCCESS.code);
        this.timeResponse = new Date();
    }
}
