package com.heima.admin.service;
public interface WemediaNewsAutoScanService {
    /**
     * 自媒体文章审核
     * @param wmNewsId
     */
    public void autoScanByMediaNewsId(Integer wmNewsId);
}