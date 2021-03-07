package com.itheima.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/CheckcodeServlet")
public class CheckcodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  创建画布
        int width = 120;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //  获得画笔
        Graphics g = bufferedImage.getGraphics();
        //  填充背景颜色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //  绘制边框
        g.setColor(Color.red);
        g.drawRect(0, 0, width - 1, height - 1);
        //  生成随机字符
        //  准备数据
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        //  准备随机对象
        Random r = new Random();
        //  声明一个变量 保存验证码
        String code = "";
        //  书写4个随机字符
        for (int i = 0; i < 4; i++) {
            //  设置字体
            g.setFont(new Font("宋体", Font.BOLD, 28));
            //  设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            String str = data.charAt(r.nextInt(data.length())) + "";
            g.drawString(str, 10 + i * 28, 30);

            //  将新的字符 保存到验证码中
            code = code + str;
        }
        //  绘制干扰线
        for (int i = 0; i < 6; i++) {
            //  设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }

        //  将验证码 打印到控制台
        System.out.println("服务器生成的验证码"+code);

        // TODO:  将验证码放到session中
        request.getSession().setAttribute("code_session", code);

        //  将画布显示在浏览器中
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}