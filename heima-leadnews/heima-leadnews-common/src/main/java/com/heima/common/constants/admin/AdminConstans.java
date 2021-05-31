package com.heima.common.constants.admin;

public class AdminConstans {
    public static final Short WAIT_AUTH = 1;
    public static final Short PASS_AUTH = 9;
    public static final Short FAIL_AUTH = 2;
    public static final Integer AUTHOR_TYPE = 2; // 自媒体用户

    public static final String APARTICLECONFIG_KEY = "apArticleConfig";
    public static final String APARTICLECONTECT_KEY = "apArticleContent";
    public static final String APAUTHOR_KEY = "apAuthor";

    public static final String ES_INDEX_TAG_ARTICLE="article";

    public static final String CL_NEWS_REVIEW_FAIL = "cl_news_review_fail";
    public static final String CL_NEWS_REVIEW_SUCCESS = "cl_news_review_success";

    public static final String TOPIC_AUTO_AUTH="topic_auto_auth";//自动认证kafka的消息名称
    public static final String TOPIC_AUTO_ARTICLE="topic_auto_article";//文章自动审核
}