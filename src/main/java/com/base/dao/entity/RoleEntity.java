package com.base.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tbl_role")
public class RoleEntity {
    @Id
    private String id;
    private String roleName;
    private boolean active = true;
}
