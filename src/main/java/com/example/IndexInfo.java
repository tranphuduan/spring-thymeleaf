package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
public class IndexInfo {
    String id;
    String imageName;
    String title;
    String time;
    String views;

    public IndexInfo(String id,String imageName, String title, String time, String views) {
        this.id=id;
        this.imageName = imageName;
        this.title = title;
        this.time = time;
        this.views = views;
    }
}
