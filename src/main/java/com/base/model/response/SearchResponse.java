package com.base.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
@Accessors(chain = true)
public class SearchResponse<T> {
    Integer totalData;
    Integer pageIndex;
    Integer pageSize;
    Integer totalPage;
    T info;
}
