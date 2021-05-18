package com.tanhua.dubbo.huanxin.api.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.HuanXinApi;
import com.tanhua.dubbo.enums.HuanXinMessageType;
import com.tanhua.dubbo.huanxin.config.HuanXinConfig;
import com.tanhua.dubbo.huanxin.service.RequestService;
import com.tanhua.dubbo.huanxin.service.TokenService;
import com.tanhua.dubbo.mapper.HuanXinUserMapper;
import com.tanhua.dubbo.pojo.HuanXinUser;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@DubboService(version = "1.0.0")
public class HuanXinApiImpl implements HuanXinApi {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HuanXinConfig huanXinConfig;

    @Autowired
    private RequestService requestService;

    @Autowired
    private HuanXinUserMapper huanXinUserMapper;

    @Override
    public String getToken() {
        return this.tokenService.getToken();
    }

    /**
     * 注册环信用户
     * 参见：http://docs-im.easemob.com/im/server/ready/user#%E6%B3%A8%E5%86%8C%E5%8D%95%E4%B8%AA%E7%94%A8%E6%88%B7_%E5%BC%80%E6%94%BE
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public Boolean register(Long userId) {
        //注册用户到环信
        String url = StrUtil.format("{}{}/{}/users",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName());

        Map<String, Object> param = new HashMap<>();
        param.put("username", "HX_" + userId);
        param.put("password", IdUtil.simpleUUID());
        param.put("nickname", "HX_" + userId);

        HttpResponse response = this.requestService.execute(url, Method.POST, param);
        if (null == response) {
            //注册失败
            return false;
        }
        //注册成功，将环信账号写入到mysql中
        HuanXinUser huanXinUser = new HuanXinUser();
        huanXinUser.setUsername(Convert.toStr(param.get("username")));
        huanXinUser.setPassword(Convert.toStr(param.get("password")));
        huanXinUser.setNickname(Convert.toStr(param.get("nickname")));
        huanXinUser.setUserId(userId);
        huanXinUser.setCreated(new Date());
        huanXinUser.setUpdated(huanXinUser.getCreated());

        return this.huanXinUserMapper.insert(huanXinUser) == 1;
    }

    /**
     * 根据用户Id询环信账户信息
     *
     * @param userId
     * @return
     */
    @Override
    public HuanXinUser queryHuanXinUser(Long userId) {
        QueryWrapper<HuanXinUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.huanXinUserMapper.selectOne(wrapper);
    }

    @Override
    public HuanXinUser queryUserByUserName(String userName) {
        QueryWrapper<HuanXinUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return this.huanXinUserMapper.selectOne(wrapper);
    }

    @Override
    public Boolean addUserFriend(Long userId, Long friendId) {
        String url = StrUtil.format("{}{}/{}/users/{}/contacts/users/{}",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName(),
                "HX_" + userId,
                "HX_" + friendId);

        //添加好友
        HttpResponse response = this.requestService.execute(url, Method.POST, null);
        return response != null;
    }

    @Override
    public Boolean removeUserFriend(Long userId, Long friendId) {
        String url = StrUtil.format("{}{}/{}/users/{}/contacts/users/{}",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName(),
                "HX_" + userId,
                "HX_" + friendId);

        //删除好友
        HttpResponse response = this.requestService.execute(url, Method.DELETE, null);
        return response != null;
    }

    /**
     * 以管理员身份发送消息
     * 文档地址：http://docs-im.easemob.com/im/server/basics/messages#%E5%8F%91%E9%80%81%E6%B6%88%E6%81%AF
     *
     * @param targetUserName     发送目标的用户名
     * @param huanXinMessageType 消息类型
     * @param msg
     * @return
     */
    @Override
    public Boolean sendMsgFromAdmin(String targetUserName, HuanXinMessageType huanXinMessageType, String msg) {
        String url = StrUtil.format("{}{}/{}/messages",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName());

        Map<String, Object> param = new HashMap<>();
        param.put("target_type", "users");
        param.put("target", Arrays.asList(targetUserName));

        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("type", huanXinMessageType.getType());
        msgMap.put("msg", msg);

        param.put("msg", msgMap);
        param.put("from", "admin"); //指定管理员
        return this.requestService.execute(url, Method.POST, param) != null;
    }
}
