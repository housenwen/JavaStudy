package com.heima.wemedia.filter;

import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.pojo.WmUser;
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
 * @创建日期 2021/5/26 10:35
 **/
@WebFilter(value = "wmTokenFilter",urlPatterns = "/*")
@Component
@Slf4j
public class WmTokenFilter extends GenericFilterBean {
    /**
     * 过滤方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 获取请求对象
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 2. 获取请求头中 userId的值
        String userId = request.getHeader("userId");
        // 3. 将用户信息保存到threadlocal中
        if(StringUtils.isNotBlank(userId)){
            WmUser wmUser = new WmUser();
            wmUser.setId(Integer.valueOf(userId));
            WmThreadLocalUtils.setUser(wmUser); // 存储登录用户信息  到线程局部变量
        }
        // 4. 放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
