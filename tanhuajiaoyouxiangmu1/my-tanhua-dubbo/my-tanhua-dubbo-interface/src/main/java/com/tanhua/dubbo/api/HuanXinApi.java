package com.tanhua.dubbo.api;

import com.tanhua.dubbo.enums.HuanXinMessageType;
import com.tanhua.dubbo.pojo.HuanXinUser;

/**
 * 与环信平台集成的相关操作
 */
public interface HuanXinApi {

    /**
     * 获取环信token（获取管理员权限）
     * 参见：http://docs-im.easemob.com/im/server/ready/user#%E8%8E%B7%E5%8F%96%E7%AE%A1%E7%90%86%E5%91%98%E6%9D%83%E9%99%90
     *
     * @return
     */
    String getToken();

    /**
     * 注册环信用户
     * 参见：http://docs-im.easemob.com/im/server/ready/user#%E6%B3%A8%E5%86%8C%E5%8D%95%E4%B8%AA%E7%94%A8%E6%88%B7_%E5%BC%80%E6%94%BE
     *
     * @param userId 用户id
     * @return
     */
    Boolean register(Long userId);

    /**
     * 根据用户Id询环信账户信息
     *
     * @param userId
     * @return
     */
    HuanXinUser queryHuanXinUser(Long userId);

    /**
     * 根据环信用户名查询用户信息
     *
     * @param userName
     * @return
     */
    HuanXinUser queryUserByUserName(String userName);

    /**
     * 添加好友（双向好友关系）
     * 参见：http://docs-im.easemob.com/im/server/ready/user#%E6%B7%BB%E5%8A%A0%E5%A5%BD%E5%8F%8B
     *
     * @param userId   自己的id
     * @param friendId 好友的id
     * @return
     */
    Boolean addUserFriend(Long userId, Long friendId);

    /**
     * 删除好友关系（双向删除）
     * 参见：http://docs-im.easemob.com/im/server/ready/user#%E7%A7%BB%E9%99%A4%E5%A5%BD%E5%8F%8B
     *
     * @param userId   自己的id
     * @param friendId 好友的id
     * @return
     */
    Boolean removeUserFriend(Long userId, Long friendId);

    /**
     * 以管理员身份发送消息
     * 文档地址：http://docs-im.easemob.com/im/server/basics/messages#%E5%8F%91%E9%80%81%E6%B6%88%E6%81%AF
     *
     * @param targetUserName     发送目标的用户名
     * @param huanXinMessageType 消息类型
     * @param msg
     * @return
     */
    Boolean sendMsgFromAdmin(String targetUserName, HuanXinMessageType huanXinMessageType, String msg);


}