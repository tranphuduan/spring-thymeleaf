package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
public class IndexInfo {
    String imageName;
    String title;
    String time;
    String views;

    public IndexInfo(String imageName, String title, String time, String views) {
        this.imageName = imageName;
        this.title = title;
        this.time = time;
        this.views = views;
    }
}
