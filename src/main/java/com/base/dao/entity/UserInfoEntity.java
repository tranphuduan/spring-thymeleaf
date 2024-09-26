package com.base.dao.entity;

import com.base.utils.AESUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "tbl_usr_inf")
@Accessors(chain = true)
public class UserInfoEntity {

    @Id
    private String id;

    private String account;

    private String username;

    private String alias;

    private String fullName;

    @JsonIgnore
    private Date modifiedDate;

    public void encrypt() {
        this.account = AESUtil.encrypt(this.account);
    }

    public void decrypt() {
        this.account = AESUtil.decrypt(this.account);
    }
}
