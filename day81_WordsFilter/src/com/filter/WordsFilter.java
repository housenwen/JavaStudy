package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@WebFilter(urlPatterns = "/*")
public class WordsFilter implements Filter {

    private List<String> wordList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 1.加载配置文件
        /**
         ResourceBundle专门读取src目录下的properties配置文件，不需要写后缀名
         */
        ResourceBundle words = ResourceBundle.getBundle("words");
        // 2.读取keyword关键字内容
        String keyWord = words.getString("keyword");
        // 3.split切割，转为list集合
        wordList = Arrays.asList(keyWord.split(","));
        System.out.println("加载非法词库："+wordList);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        //向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取用户输入的值
        String content = request.getParameter("username");

        // 2.拦截非法内容，提示
        for (String word:wordList){ // 遍历非法词库

            if (content!=null){
                if (content.contains(word)){
                    // 判断是否包含非法词汇
                    response.getWriter().write("你输入的词汇敏感，拦截了。。。");
                    return;
                }
            }

        }
        //放行
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
