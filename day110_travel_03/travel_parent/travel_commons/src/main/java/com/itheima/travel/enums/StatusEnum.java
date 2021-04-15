package com.itheima.travel.enums;

/**
 * @Description：
 */
public enum StatusEnum {
    SUCCEED("200","操作成功"),
    FAIL("1000","操作失败"),
    NO_LOGIN("1001", "请登录"),
    LOGIN_FAILURE("1002", "登录失败"),
    LOGOUT("1003", "注销成功"),
    NO_AUTH("1004", "权限不足"),
    NO_ROLE("1005", "角色不符合"),
    UPLOAD_FAIL("1006", "上传失败"),
    BIND_BUSINESS_FAIL("1007", "附件绑定业务失败"),
    FIND_AFFIX_BUSINESSID_FAIL("1008", "查询业务对应附件失败"),
    FIND_ALL_CATEGORY_FAIL("1009", "查询分类失败"),
    ADD_FAVORITE_FAIL("1010", "添加收藏失败"),
    FIND_MYFAVORITE_FAIL("1011", "查询我的收藏失败"),
    ISFAVORITED_FAIL("1013", "查询是否收藏失败"),
    ADD_ROUTE_FAIL("1014", "添加线路失败"),
    UPDATE_ROUTE_FAIL("1015", "修改线路失败"),
    FIND_ROUTE_ID_FAIL("1016", "查询线路失败"),
    FIND_ROUTE_PAGE_FAIL("1017", "分页查询线路失败"),
    REGISTER_USER_FAIL("1018", "注册用户失败"),
    LOGIN_USER_FAIL("1019", "用户登录失败"),
    ISLOGIN_FAIL("1020", "是否登录查询失败")

    ;

    private String code;
    private String msg;

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
