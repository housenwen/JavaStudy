package com.heima.wemedia.filter;

import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.pojos.WmUser;
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
 * @创建日期 2021/7/6 15:34
 **/
@WebFilter(value = "wmTokenFilter",urlPatterns = "/*")
@Component
@Slf4j
public class WmTokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 前置操作
        // 1. 获取请求头userId
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userId = request.getHeader("userId");
        // 2. 设置到本地线程变量
        if(StringUtils.isNotBlank(userId)){
            log.info("用户id ==> {} 登录到自媒体微服务中  ",userId);
            WmUser wmUser = new WmUser();
            wmUser.setId(Integer.valueOf(userId));
            WmThreadLocalUtils.setUser(wmUser);
        }
        filterChain.doFilter(servletRequest,servletResponse);
        // 后置操作
        // 3. 线程调用完毕后，清空登录信息
        WmThreadLocalUtils.clear();
    }
}
