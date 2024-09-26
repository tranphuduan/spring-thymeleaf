package com.base.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "photos")
public class PhotoEntity {
    @Id
    private String id;
    private String title;
    private String contentType;
    private byte[] image;
    private boolean active = true;
}
