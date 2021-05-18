package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.HuanXinApi;
import com.tanhua.dubbo.api.QuanZiApi;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.api.UsersApi;
import com.tanhua.dubbo.pojo.*;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IMService {

    @DubboReference(version = "1.0.0")
    private HuanXinApi huanXinApi;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    @DubboReference(version = "1.0.0")
    private UsersApi usersApi;

    @DubboReference(version = "1.0.0")
    private QuanZiApi quanZiApi;

    @Autowired
    private AnnouncementService announcementService;

    public UserInfoVo queryUserInfoByUserName(String userName) {
        HuanXinUser huanXinUser = this.huanXinApi.queryUserByUserName(userName);
        if (null == huanXinUser) {
            return null;
        }
        UserInfo userInfo = this.userInfoApi.queryByUserId(huanXinUser.getUserId());
        UserInfoVo userInfoVo = BeanUtil.toBeanIgnoreError(userInfo, UserInfoVo.class);
        userInfoVo.setGender(userInfo.getSex().name().toLowerCase());
        userInfoVo.setMarriage(StrUtil.equals("已婚", userInfo.getMarriage()) ? 1 : 0);
        return userInfoVo;
    }

    public Object addContacts(Long friendId) {
        Long userId = UserThreadLocal.get();
        //将好友关系写入到MongoDB
        String result = this.usersApi.saveUsers(userId, friendId);
        if (StrUtil.isEmpty(result)) {
            return ErrorResult.builder()
                    .errCode("5001")
                    .errMessage("添加好友好像到MongoDB失败！")
                    .build();
        }

        //将好友关系写入到环信
        Boolean hxResult = this.huanXinApi.addUserFriend(userId, friendId);
        if (!hxResult) {
            return ErrorResult.builder()
                    .errCode("5002")
                    .errMessage("添加好友关系到环信失败！")
                    .build();
        }

        return null;
    }

    /**
     * 删除好友关系
     * @param friendId
     * @return
     */
    public Object removeContacts(Long friendId) {
        Long userId = UserThreadLocal.get();
        //删除MongoDB好友关系
        Boolean result = this.usersApi.removeUsers(userId, friendId);
        if (result == null) {
            return ErrorResult.builder()
                    .errCode("5001")
                    .errMessage("删除MongoDB好友关系失败！")
                    .build();
        }
        //删除环信好友关系
        Boolean hxResult = this.huanXinApi.removeUserFriend(userId, friendId);
        if (!hxResult) {
            return ErrorResult.builder()
                    .errCode("5002")
                    .errMessage("删除环信好友失败！")
                    .build();
        }
        return null;
    }

    public PageResult queryContactsList(Integer page, Integer pageSize, String keyword) {
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);

        Long userId = UserThreadLocal.get();
        List<UserInfo> friendUserInfoList;
        if (StrUtil.isEmpty(keyword)) {
            //分页查询
            PageInfo<Users> pageInfo = this.usersApi.queryUsersList(userId, page, pageSize);
            List<Object> friendIdList = CollUtil.getFieldValues(pageInfo.getRecords(), "friendId");
            friendUserInfoList = this.userInfoApi.queryByUserIdList(friendIdList);
        } else {
            //按照用户名like查询
            List<Users> usersList = this.usersApi.queryAllUsersList(userId);
            List<Object> friendIdList = CollUtil.getFieldValues(usersList, "friendId");
            friendUserInfoList = this.userInfoApi.queryLikeUserName(friendIdList, keyword);
        }

        List<UsersVo> usersVoList = new ArrayList<>();

        friendUserInfoList.forEach(userInfo -> {
            UsersVo usersVo = BeanUtil.toBeanIgnoreError(userInfo, UsersVo.class);
            usersVo.setUserId("HX_" + userInfo.getUserId());
            usersVo.setGender(userInfo.getSex().name().toLowerCase());
            usersVo.setCity(StrUtil.subBefore(userInfo.getCity(), '-', false));
            usersVoList.add(usersVo);
        });

        pageResult.setItems(usersVoList);
        return pageResult;
    }

    public PageResult queryLikeCommentById(Integer page,Integer pageSize){
        Long userId = UserThreadLocal.get();
        PageInfo<Comment> commentPageInfo = this.quanZiApi.queryLikeById(userId, page, pageSize);
        PageResult pageResult = this.fillUserCommentList(commentPageInfo);
        return pageResult;
    }

    private PageResult fillUserCommentList(PageInfo<Comment> pageInfo){
        PageResult pageResult = new PageResult();
        pageResult.setPage(pageInfo.getPageNum());
        pageResult.setPagesize(pageInfo.getPageSize());

        List<Comment> records = pageInfo.getRecords();
        if (CollUtil.isEmpty(records)){
            //没有找到数据
            return pageResult;
        }
        List<Object> userId = CollUtil.getFieldValues(records, "userId");
        List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(userId);
        List<MessageCommentVo> messageCommentVoList=new ArrayList<>();
        for (Comment record : records) {
            for (UserInfo userInfo : userInfoList) {
                if (ObjectUtil.equal(record.getUserId(),userInfo.getUserId())){
                    MessageCommentVo messageCommentVo = new MessageCommentVo();
                    messageCommentVo.setId(record.getId().toString());
                    messageCommentVo.setCreateDate(DateUtil.format(new Date(record.getCreated()),"yyyy-MM-dd"));
                    messageCommentVo.setAvatar(userInfo.getLogo());
                    messageCommentVo.setNickname(userInfo.getNickName());

                    messageCommentVoList.add(messageCommentVo);
                    break;
                }
            }
        }
        pageResult.setItems(messageCommentVoList);
        return pageResult;
    }

    public PageResult queryCommentsById(Integer page, Integer pageSize) {
        Long userId = UserThreadLocal.get();
        PageInfo<Comment> commentPageInfo = this.quanZiApi.queryCommentById(userId, page, pageSize);
        PageResult pageResult = this.fillUserCommentList(commentPageInfo);
        return pageResult;
    }


    public PageResult queryLoveById(Integer page,Integer pageSize){
        Long userId = UserThreadLocal.get();
        PageInfo<Comment> commentPageInfo = this.quanZiApi.queryLoveById(userId, page, pageSize);
        PageResult pageResult = this.fillUserCommentList(commentPageInfo);
        return pageResult;
    }

    public PageResult queryMessageAnnouncementList(Integer page, Integer pageSize) {
        IPage<Announcement> announcementIPage = this.announcementService.queryList(page, pageSize);
        List<AnnouncementVo> voList=new ArrayList<>();
        for (Announcement announcement : announcementIPage.getRecords()) {
            AnnouncementVo announcementVo = new AnnouncementVo();
            announcementVo.setId(announcement.getId().toString());
            announcementVo.setTitle(announcement.getTitle());
            announcementVo.setDescription(announcement.getDescription());
            announcementVo.setCreateDate(DateUtil.format(announcement.getCreated(),"yyyy-MM-dd"));
            voList.add(announcementVo);
        }
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPage(pageSize);
        pageResult.setItems(voList);
        return pageResult;
    }
}
