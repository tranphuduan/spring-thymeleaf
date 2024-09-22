package com.example;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
@Accessors(chain = true)
public class SearchResponse {
    Integer totalData;
    Integer pageIndex;
    Integer pageSize;
    List<IndexInfo> info;


}
