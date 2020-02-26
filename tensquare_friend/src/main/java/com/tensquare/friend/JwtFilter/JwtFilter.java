package com.tensquare.friend.JwtFilter;

import com.sun.org.glassfish.gmbal.IncludeSubclass;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置一个适配器然后将适配器加到拦截器里
 * 所有的请求都要讲过这个适配器，这个适配器将所有的请求都允许通过，
 * 最后在service里判断是否有权限访问
 */
@Component
@SuppressWarnings("all")
public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7); // The part after "Bearer "
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if("admin".equals(claims.get("roles"))) {//如果是管理员
                    request.setAttribute("admin_claims", claims);  //将claims提取出来
                }

                if("user".equals(claims.get("roles"))){//如果是用户
                    request.setAttribute("user_claims", claims); //将claims提取出来
                }
            }

        }
        return true;    //表示所有请求都允许通过
    }
}
