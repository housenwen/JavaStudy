package com.heima.user.filter;

import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @作者 itcast
 * @创建日期 2021/6/2 14:26
 **/
@Slf4j
@WebFilter(filterName = "appTokenFilter",urlPatterns = "/*")
@Component
public class AppTokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1. 获取请求和响应
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //2. 获取请求头中的userId信息
        String userId = request.getHeader("userId");
        //3. 将userId封装成apUser存入到ThreadLocal中
        if(StringUtils.isNotBlank(userId)&&Integer.valueOf(userId)!=0){
            ApUser apUser = new ApUser();
            apUser.setId(Integer.valueOf(userId));
            AppThreadLocalUtils.setUser(apUser);
            log.info(" 当前访问用户的id ==>{}",apUser.getId());
        }
        //4. 放行请求
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
