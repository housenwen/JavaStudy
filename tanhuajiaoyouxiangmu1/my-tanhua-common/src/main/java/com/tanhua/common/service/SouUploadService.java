package com.tanhua.common.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.tanhua.common.config.AliyunOSSConfig;
import com.tanhua.common.vo.SoundUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Service
public class SouUploadService {

    // 允许上传的格式
    private static final String[] SOUND_TYPE = new String[]{".m4a"};

    @Autowired
    private OSS oss;

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    public SoundUploadResult upload(MultipartFile uploadFile) {

        SoundUploadResult soundUploadResult = new SoundUploadResult();

        //语音做校验，对后缀名
        boolean isLegal = false;

        for (String type : SOUND_TYPE) {
            if (StrUtil.endWithIgnoreCase(uploadFile.getOriginalFilename(),
                    type)) {
                isLegal = true;
                break;
            }
        }

        if (!isLegal) {
            soundUploadResult.setStatus("error");
            return soundUploadResult;
        }

        // 文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);

        // 上传到阿里云
        try {
            // 目录结构：sounds/2018/12/29/xxxx.jpg
            oss.putObject(this.aliyunOSSConfig.getBucketName(), filePath, new
                    ByteArrayInputStream(uploadFile.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            soundUploadResult.setStatus("error");
            return soundUploadResult;
        }

        // 上传成功
        soundUploadResult.setStatus("done");
        soundUploadResult.setName(this.aliyunOSSConfig.getUrlPrefix() + filePath);
        soundUploadResult.setUid(String.valueOf(System.currentTimeMillis()));

        return soundUploadResult;
    }

    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "sounds/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtil.randomInt(100, 9999) + "." +
                StrUtil.subAfter(sourceFileName, '.', true);
    }

}