package com.heima.model.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageResponseResult extends ResponseResult implements Serializable {
    private Integer currentPage;
    private Integer size;
    private Long total;
    public PageResponseResult(Integer currentPage, Integer size, Long total) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
    }
    public PageResponseResult() {

    }
}
