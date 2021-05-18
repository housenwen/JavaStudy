package com.tanhua.sso.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 启动后，读取RSA的公钥与私钥文件中的内容
 * 如果，公钥和私钥的文件都不存在，就生成新的公钥和私钥
 */
@Data
@Service
public class RSAService {

    private String privateKey;
    private String publicKey;

    @Value("${tanhua.rsa.dir}")
    private String rsaDir;

    @PostConstruct //在spring容器初始化完成后执行
    public void init() {
        //读取私钥文件
        String privateKeyFile = rsaDir + File.separator + "rsa";
        if (FileUtil.exist(privateKeyFile)) {
            this.privateKey = FileUtil.readString(privateKeyFile, CharsetUtil.CHARSET_UTF_8);
        }

        //读取公钥文件
        String publicKeyFile = rsaDir + File.separator + "rsa.pub";
        if (FileUtil.exist(publicKeyFile)) {
            this.publicKey = FileUtil.readString(publicKeyFile, CharsetUtil.CHARSET_UTF_8);
        }

        if (!StrUtil.isAllEmpty(this.publicKey, this.privateKey)) {
            return;
        }

        //首次使用，需要生成新的RSA的公钥、私钥
        RSA rsa = new RSA();
        //私钥
        FileUtil.writeString(rsa.getPrivateKeyBase64(), privateKeyFile, CharsetUtil.CHARSET_UTF_8);

        //公钥
        FileUtil.writeString(rsa.getPublicKeyBase64(), publicKeyFile, CharsetUtil.CHARSET_UTF_8);

        this.privateKey = rsa.getPrivateKeyBase64();
        this.publicKey = rsa.getPublicKeyBase64();
    }

}
