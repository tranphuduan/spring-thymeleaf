package com.base.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tbl_perm")
public class PermissionEntity {
    @Id
    private String id;
    private String permission;
    private List<String> roleList;
    private boolean active = true;
}
