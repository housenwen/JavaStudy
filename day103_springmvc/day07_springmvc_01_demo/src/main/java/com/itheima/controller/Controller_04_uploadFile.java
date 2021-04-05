package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class Controller_04_uploadFile {




    @RequestMapping("/uploadFile")
    public String uploadFile(Model model, MultipartFile file,String username){

        //获取原文件名,进行重新命名，防止文件名一致的情况下，文件覆盖问题
        String filename = UUID.randomUUID().toString()+file.getOriginalFilename();


        //图片按照业务模块进行存储
        String businessType = "user";
        //将图片保存到对应的文件服务器对应的模块文件下
        String directoryPath = "E:\\tomcat\\apache-tomcat-8.5.32\\webapps\\imgServer\\"+businessType;
        File directory = new File(directoryPath);
        if(!directory.exists()){
            //创建对应模块的文件夹
            directory.mkdir();
        }

        //图片的存储路径
        String savePath = "E:\\tomcat\\apache-tomcat-8.5.32\\webapps\\imgServer\\"+businessType+"\\"+filename;
        //保存文件
        try {
            //file.transferTo(new File("f:/"+filename));
            //图片应该保存到图片服务器中
            file.transferTo(new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //图片的http协议的访问路径，应该保存到数据库
        String dbPath = "http://localhost:8088/imgServer/"+businessType+"/"+filename;

        //保存到数据库
        model.addAttribute("url",dbPath);
        model.addAttribute("msg","文件上传:表单其他参数："+username);
        return "demo";
    }


    /**
     * 文件的下载，就是将文件作为字节响应给浏览器
     * @param response
     */
    public void download(HttpServletResponse response){
        String savePath = "E:\\tomcat\\apache-tomcat-8.5.32\\webapps\\imgServer\\"+"";
        try {
            FileInputStream fs = new FileInputStream(savePath);
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=fs.read(buf))!=-1){
                response.getOutputStream().write(buf,0,len);
            }

            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
