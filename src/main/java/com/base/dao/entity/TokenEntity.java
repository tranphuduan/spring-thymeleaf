package com.base.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "tbl_token")
@Accessors(chain = true)
public class TokenEntity {

    @Id
    private String id;

    private String username;

    private String alias;

    private String token;

    private List<String> roles;

    private String ip;

    @JsonIgnore
    private Date modifiedDate;

    @JsonIgnore
    private Date expTime;

}
