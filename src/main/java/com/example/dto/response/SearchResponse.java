package com.example.dto.response;

import com.example.IndexInfo;
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
    Integer totalPage;
    List<IndexInfo> info;


}
