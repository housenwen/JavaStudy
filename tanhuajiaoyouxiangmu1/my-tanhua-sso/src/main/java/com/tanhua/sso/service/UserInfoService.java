package com.tanhua.sso.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.service.PicUploadService;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.common.vo.PicUploadResult;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.enums.SexEnum;
import com.tanhua.dubbo.pojo.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    private UserService userService;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    @Autowired
    private PicUploadService picUploadService;

    @Autowired
    private FaceImageService faceImageService;


    public Object loginReginfo(Map<String, Object> param, String token) {
        //对token进行校验
        Long userId = this.userService.checkToken(token);
        if (null == userId) {
            return ErrorResult.builder()
                    .errCode("401")
                    .errMessage("token过期或不合法").build();
        }

        UserInfo userInfo = BeanUtil.toBeanIgnoreError(param, UserInfo.class);
        userInfo.setUserId(userId);
        userInfo.setNickName(Convert.toStr(param.get("nickname")));
        userInfo.setSex(StrUtil.equals(Convert.toStr(param.get("gender")), "man") ? SexEnum.MAN : SexEnum.WOMAN);
        Boolean result = this.userInfoApi.save(userInfo);

        if (result) {
            //保存成功
            return null;
        }
        return ErrorResult.builder()
                .errCode("500")
                .errMessage("用户基本信息保存失败！").build();
    }

    public Object userLogo(String token, MultipartFile multipartFile) {
        //对token进行校验
        Long userId = this.userService.checkToken(token);
        if (null == userId) {
            return ErrorResult.builder()
                    .errCode("401")
                    .errMessage("token过期或不合法").build();
        }

        //上传图片到阿里云OSS
        PicUploadResult uploadResult = this.picUploadService.upload(multipartFile);
        if (StrUtil.isEmpty(uploadResult.getName())) {
            return ErrorResult.builder()
                    .errCode("5001")
                    .errMessage("头像上传失败！").build();
        }

        //校验头像是否为人像
        if (!this.faceImageService.checkIsPortrait(uploadResult.getName())) {
            //TODO 删除OSS中的图片:已完成**************
            this.picUploadService.delete(uploadResult.getName());

            return ErrorResult.builder()
                    .errCode("5002")
                    .errMessage("图片非人像，请重新上传！").build();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setLogo(uploadResult.getName());
        userInfo.setCoverPic(userInfo.getLogo());
        userInfo.setUserId(userId);
        Boolean result = this.userInfoApi.update(userInfo);
        if (result) {
            return null;
        }

        return ErrorResult.builder()
                .errCode("5003")
                .errMessage("头像上传失败！").build();
    }
}
