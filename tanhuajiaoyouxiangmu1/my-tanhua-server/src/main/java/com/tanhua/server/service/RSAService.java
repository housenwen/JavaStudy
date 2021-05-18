package com.tanhua.server.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 启动后，读取RSA的公钥文件中的内容
 */
@Data
@Service
public class RSAService {

    private String publicKey;

    @Value("${tanhua.rsa.dir}")
    private String rsaDir;

    @PostConstruct //在spring容器初始化完成后执行
    public void init() {
        //读取公钥文件
        String publicKeyFile = rsaDir + File.separator + "rsa.pub";
        if (FileUtil.exist(publicKeyFile)) {
            this.publicKey = FileUtil.readString(publicKeyFile, CharsetUtil.CHARSET_UTF_8);
        }

        if (StrUtil.isEmpty(this.publicKey)) {
            //公钥文件不存在
            String msg = StrUtil.format("RSA公钥文件 {} 不存在！请检查文件！", publicKeyFile);
            throw new RuntimeException(msg);
        }
    }

}
