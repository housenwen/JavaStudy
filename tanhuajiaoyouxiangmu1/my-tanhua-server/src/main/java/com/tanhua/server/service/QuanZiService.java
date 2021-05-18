package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.service.PicUploadService;
import com.tanhua.common.utils.LocationUtils;
import com.tanhua.common.utils.RelativeDateFormat;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.common.vo.PicUploadResult;
import com.tanhua.dubbo.api.*;
import com.tanhua.dubbo.pojo.Comment;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.dubbo.pojo.Visitors;
import com.tanhua.dubbo.vo.CommentVo;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.dubbo.vo.UserLocationVo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.QuanZiVo;
import com.tanhua.server.vo.VisitorsAllVo;
import com.tanhua.server.vo.VisitorsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuanZiService {

    @DubboReference(version = "1.0.0")
    private RecommendUserApi recommendUserApi;

    @DubboReference(version = "1.0.0")
    private QuanZiApi quanZiApi;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    @DubboReference(version = "1.0.0")
    private VisitorsApi visitorsApi;
    @DubboReference(version = "1.0.0")
    private UserLikeApi userLikeApi;
    @Autowired
    private PicUploadService picUploadService;

    @Autowired
    private QuanziMQService quanziMQService;

    @DubboReference(version = "1.0.0")
    private UserLocationApi userLocationApi;

    @Value("${tanhua.sso.defaults.pidList}")
    private List<Long> pidList;

    public PageResult queryPublishList(Integer page, Integer pageSzie) {
        Long userId = UserThreadLocal.get();

        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSzie);

        //查询到了动态列表
        PageInfo<Publish> pageInfo = this.quanZiApi.queryPublishList(userId, page, pageSzie);
        List<Publish> publishList = pageInfo.getRecords();
        if (CollUtil.isEmpty(publishList)) {
            return pageResult;
        }
        pageResult.setItems(this.fillQuanZiVoList(publishList));
        return pageResult;
    }

    // 调用LocationUtils工具类实现查询距离
    public Double queryDistance(Publish publish){
        Long userId = UserThreadLocal.get();
        UserLocationVo userLocation = userLocationApi.queryByUserId(userId);
        Double longitude = userLocation.getLongitude();
        Double latitude = userLocation.getLatitude();
        Double longitude1 = Convert.toDouble(publish.getLongitude());
        Double latitude1 = Convert.toDouble(publish.getLatitude());
        return LocationUtils.getDistance(latitude,longitude,latitude1,longitude1);

    }

    private List<QuanZiVo> fillQuanZiVoList(List<Publish> publishList) {


        //将Publish对象封装成QuanZiVo对象
        List<QuanZiVo> quanZiVoList = new ArrayList<>();

        //查询发布动态的用户信息列表
        List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(CollUtil
                .getFieldValues(publishList, "userId"));

        Long userId = UserThreadLocal.get();

        for (Publish publish : publishList) {

            QuanZiVo quanZiVo = BeanUtil.toBeanIgnoreError(publish, QuanZiVo.class);
            quanZiVo.setDistance(queryDistance(publish) + "公里"); //TODO 计算距离 已完成
            quanZiVo.setCreateDate(RelativeDateFormat.format(new Date(publish.getCreated()))); //发布时间差：10分钟前
            String publishId = publish.getId().toString();
            quanZiVo.setLikeCount(this.quanZiApi.queryLikeCount(publishId).intValue()); //点赞数
            quanZiVo.setLoveCount(this.quanZiApi.queryLoveCount(publishId).intValue()); //TODO 喜欢数:已完成**************
            quanZiVo.setCommentCount(this.quanZiApi.queryCommentCount(publishId).intValue()); //TODO 评论数 已完成
            quanZiVo.setHasLoved(this.quanZiApi.queryUserIsLove(userId, publishId) ? 1 : 0); //TODO 是否喜欢:已完成**************
            quanZiVo.setHasLiked(this.quanZiApi.queryUserIsLike(userId, publishId) ? 1 : 0); //是否点赞

            for (UserInfo userInfo : userInfoList) {
                if (ObjectUtil.equal(userInfo.getUserId(), publish.getUserId())) {
                    BeanUtil.copyProperties(userInfo, quanZiVo, "id");
                    quanZiVo.setGender(userInfo.getSex().name().toLowerCase());
                    break;
                }
            }

            quanZiVoList.add(quanZiVo);
        }

        return quanZiVoList;
    }

    public Object savePublish(String textContent, String location,
                              String latitude, String longitude,
                              MultipartFile[] multipartFile) {

        Publish publish = new Publish();
        publish.setText(textContent);
        publish.setLocationName(location);
        publish.setLongitude(longitude);
        publish.setLatitude(latitude);
        publish.setMedias(new ArrayList<>());
        publish.setUserId(UserThreadLocal.get());

        //上传图片到阿里云
        for (MultipartFile file : multipartFile) {
            PicUploadResult uploadResult = this.picUploadService.upload(file);
            if (StrUtil.isNotEmpty(uploadResult.getName())) {
                //上传成功
                publish.getMedias().add(uploadResult.getName());
            }
        }

        String publishId = this.quanZiApi.savePublish(publish);
        if (StrUtil.isEmpty(publishId)) {
            //发布失败
            return ErrorResult.builder()
                    .errMessage("发布动态失败！")
                    .errCode("5001")
                    .build();
        }

        //发消息
        this.quanziMQService.publishMsg(publishId);

        return null;
    }

    public PageResult queryRecommendPublish(Integer page, Integer pageSize) {
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);

        Long userId = UserThreadLocal.get();
        PageInfo<Publish> pageInfo = this.quanZiApi.queryRecommendPublishList(userId, page, pageSize);
        List<Publish> publishList = pageInfo.getRecords();
        if (CollUtil.isEmpty(publishList)) {
            //TODO 给出默认没有推荐,已完成
//         Collections.shuffle(pidList) 是JavaUtils 将集合元素打乱API
            Collections.shuffle(pidList);
            //            通过集合中的api,subList(),随机获取10个元素
            List<Long> longs = pidList.subList(0, 10);
            //            将随机的元素响应给前端
            List<Publish> pageResults = this.quanZiApi.defaultPublish(longs);
            pageResult.setItems(this.fillQuanZiVoList(pageResults));
            return pageResult;
        }
        pageResult.setItems(this.fillQuanZiVoList(publishList));
        return pageResult;
    }

    /**
     * 动态点赞
     *
     * @param publishId
     * @return
     */
    public Long likeComment(String publishId) {
        Long userId = UserThreadLocal.get();
        //点赞
        Boolean result = this.quanZiApi.likeComment(userId, publishId);
        if (result) {
            //发消息
            this.quanziMQService.likePublishMsg(publishId);
        }
        return this.quanZiApi.queryLikeCount(publishId);
    }

    /**
     * 取消点赞
     *
     * @param publishId
     * @return
     */
    public Long disLikeComment(String publishId) {
        Long userId = UserThreadLocal.get();
        //点赞
        Boolean result = this.quanZiApi.disLikeComment(userId, publishId);
        if (result) {
            //发消息
            this.quanziMQService.disLikePublishMsg(publishId);
        }
        return this.quanZiApi.queryLikeCount(publishId);
    }

    /**
     * 查询自己的相册表动态
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PageResult queryAlbumList(Long userId, Integer page, Integer pageSize) {
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);

        PageInfo<Publish> pageInfo = this.quanZiApi.queryAlbumList(userId, page, pageSize);
        List<Publish> publishList = pageInfo.getRecords();
        if (CollUtil.isEmpty(publishList)) {
            return pageResult;
        }

        pageResult.setItems(this.fillQuanZiVoList(publishList));
        return pageResult;
    }

    /*
     *
     * 返回谁看过我的数据
     * */
    public PageResult queryAllVisitors(Integer page, Integer pageSize) {
        Long userId = UserThreadLocal.get();
        List<Visitors> visitors = this.visitorsApi.queryMyAllVisitor(userId, pageSize, page);
        PageResult pageResult = new PageResult();
        pageResult.setCounts(visitors.size());
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);
        int num = visitors.size() / pageSize;
        pageResult.setPages(num);
        Map<Long, UserInfo> visitorUserIdMap = this.userInfoApi
                .queryMapByUserIdList(CollUtil.getFieldValues(visitors, "visitorUserId"));
        List<VisitorsAllVo> list = new ArrayList<>();
        for (Visitors visitor : visitors) {
            UserInfo userInfo = visitorUserIdMap.get(visitor.getVisitorUserId());
            VisitorsAllVo visitorsAllVo = BeanUtil.toBeanIgnoreError(userInfo, VisitorsAllVo.class);
            visitorsAllVo.setMarriage(userInfo.getMarriage() == "已婚" ? 1 : 0);
            visitorsAllVo.setGender(userInfo.getSex().name().toLowerCase());
            Long visitorUserId = visitor.getVisitorUserId();
            Double aDouble = recommendUserApi.queryScore(userId, visitorUserId);
            Integer integer = Convert.toInt(aDouble);
            visitorsAllVo.setMatchRate(integer);
            visitorsAllVo.setAlreadyLove(this.userLikeApi.isLike(userId, visitorUserId));
            list.add(visitorsAllVo);
        }
        pageResult.setItems(list);
        return pageResult;
    }


    public List<VisitorsVo> queryVisitorsList() {
        Long userId = UserThreadLocal.get();
        List<Visitors> visitorsList = this.visitorsApi.queryMyVisitor(userId);
        if (CollUtil.isEmpty(visitorsList)) {
            return Collections.emptyList();
        }

        //访客用户信息
        // Map<Long, UserInfo> visitorUserIdMap = this.userInfoApi
        //         .queryMapByUserIdList(CollUtil.getFieldValues(visitorsList, "visitorUserId"));
        //
        // List<VisitorsVo> result = new ArrayList<>();
        // for (Visitors visitors : visitorsList) {
        //     UserInfo userInfo = visitorUserIdMap.get(visitors.getVisitorUserId());
        //     VisitorsVo visitorsVo = BeanUtil.toBeanIgnoreError(userInfo, VisitorsVo.class);
        //     visitorsVo.setGender(userInfo.getSex().name().toLowerCase());
        //
        //     result.add(visitorsVo);
        // }
        // return result;

        return this.userInfoApi
                .queryByUserIdList(CollUtil.getFieldValues(visitorsList, "visitorUserId"))
                .stream().map(userInfo -> {
                    VisitorsVo visitorsVo = BeanUtil.toBeanIgnoreError(userInfo, VisitorsVo.class);
                    visitorsVo.setGender(userInfo.getSex().name().toLowerCase());
                    return visitorsVo;
                }).collect(Collectors.toList());

    }

    /**
     * 喜欢
     *
     * @param
     * @param publishId
     * @return
     */
    public Long loveComment(String publishId) {
        Long userId = UserThreadLocal.get();
        Boolean result = this.quanZiApi.loveComment(userId, publishId);
        if(result){
            //发消息
            this.quanziMQService.lovePublishMsg(publishId);
        }
//           无论判断值是啥,都要调用方法显示该动态的喜欢数
        return this.quanZiApi.queryLoveCount(publishId);

    }

    /**
     * 取消喜欢
     *
     * @param
     * @param publishId
     * @return
     */
    public Long unLoveComment(String publishId) {
        Long userId = UserThreadLocal.get();
        Boolean result = this.quanZiApi.disLoveComment(userId, publishId);
        if(result){
            //发消息
            this.quanziMQService.disLovePublishMsg(publishId);
        }
        return this.quanZiApi.queryLoveCount(publishId);
    }

    /**
     * 查询用户是否喜欢该动态
     *
     * @param
     * @param publishId
     * @return
     */
    public Boolean userLoveComment(String publishId) {
        //        从封装好的token方法中获取用户的id
        Long userId = UserThreadLocal.get();
        //        调用方法查询用户是否喜欢该动态
        return this.quanZiApi.queryUserIsLove(userId, publishId);
    }

    /**
     * 查询评论列表
     *
     * @param publishId
     * @param page
     * @param pageSize
     * @return
     */
    public PageResult queryCommentList(String publishId, Integer page, Integer pageSize) {

        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);

        Long userId = UserThreadLocal.get();

        //查询评论列表数据
        PageInfo<Comment> pageInfo = this.quanZiApi.queryCommentList(publishId, page, pageSize);
        List<Comment> records = pageInfo.getRecords();
        if (CollUtil.isEmpty(records)) {
            return pageResult;
        }

        //查询用户信息
        List<Object> userIdList = CollUtil.getFieldValues(records, "userId");
        List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(userIdList);

        List<CommentVo> result = new ArrayList<>();
        for (Comment record : records) {
            CommentVo commentVo = new CommentVo();
            commentVo.setContent(record.getContent());
            commentVo.setId(record.getId().toHexString());
            commentVo.setCreateDate(DateUtil.format(new Date(record.getCreated()), "HH:mm"));
            //是否点赞
            commentVo.setHasLiked(this.quanZiApi.queryUserIsLike(userId, commentVo.getId()) ? 1 : 0);
            //点赞数
            commentVo.setLikeCount(Convert.toInt(this.quanZiApi.queryLikeCount(commentVo.getId())));

            for (UserInfo userInfo : userInfoList) {
                if (ObjectUtil.equals(record.getUserId(), userInfo.getUserId())) {
                    commentVo.setAvatar(userInfo.getLogo());
                    commentVo.setNickname(userInfo.getNickName());
                    break;
                }
            }
            result.add(commentVo);
        }
        pageResult.setItems(result);
        return pageResult;
    }

    /**
     * 发表评论
     *
     * @param publishId
     * @param content
     * @return
     */
    public Boolean saveComments(String publishId, String content) {
        Long userId = UserThreadLocal.get();
        return this.quanZiApi.saveComment(userId, publishId, content);

    }

}
