package com.tanhua.sso.service;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.facebody.model.v20191230.DetectFaceRequest;
import com.aliyuncs.facebody.model.v20191230.DetectFaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 校验图片是否为人像
 */
@Service
public class FaceImageService {

    @Autowired
    private IAcsClient iAcsClient;

    /**
     * 检测图片是否为人像
     *
     * @param ossImageUrl 图像对象
     * @return true:人像，false:非人像
     */
    public boolean checkIsPortrait(String ossImageUrl) {
        DetectFaceRequest req = new DetectFaceRequest();
        req.setImageURL(ossImageUrl);
        DetectFaceResponse resp;
        try {
            resp = iAcsClient.getAcsResponse(req);
            return resp.getData().getFaceCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
