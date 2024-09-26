package com.base.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String username;
    private String alias;
    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expTime;

}