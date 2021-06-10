package com.heima.common.constants.wemedia;
/**
 * @Description: 素材常量
 * @Version: V1.0
 */
public class WemediaConstants {

    // 是否收藏
    public static final Short COLLECT_MATERIAL = 1;//收藏
    public static final Short CANCEL_COLLECT_MATERIAL = 0;//取消收藏

    // 文章类型
    public static final String WM_NEWS_TYPE_IMAGE = "image";

    // 文章状态
    public static final Short WM_NEWS_DRAFT_STATUS = 0; //草稿
    public static final Short WM_NEWS_SUMMIT_STATUS = 1; //提交
    public static final Short WM_NEWS_AUTHED_STATUS = 8; //审核通过
    public static final Short WM_NEWS_PUBLISH_STATUS = 9; //已发布

    // 文章封面选图
    public static final Short WM_NEWS_NONE_IMAGE = 0; //无图
    public static final Short WM_NEWS_SINGLE_IMAGE = 1; //单图
    public static final Short WM_NEWS_MANY_IMAGE = 3; //多图
    public static final Short WM_NEWS_TYPE_AUTO = -1; //图文类型自动

    // 文章图片引用
    public static final Short WM_CONTENT_REFERENCE = 0; // 内容引用
    public static final Short WM_IMAGE_REFERENCE = 1; // 封面引用

    // 文章上架状态
    public static final Short WM_NEWS_ENABLE_UP = 1; // 文章上架状态
    public static final Short WM_NEWS_ENABLE_DOWN = 0; // 文章下架状态
}