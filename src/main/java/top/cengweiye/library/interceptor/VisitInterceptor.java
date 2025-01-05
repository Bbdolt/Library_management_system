package top.cengweiye.library.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import top.cengweiye.library.Utilities.TokenUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头获取Token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (token != null) {
                        token = token.substring(6);
                        if (TokenUtil.verify(token)) {
                            // Token有效，可以继续访问
                            return true;
                        }
                    }
                }
            }
        }
        // 如果Token无效，跳转到登录页面
        response.sendRedirect("/login.html");
        return false;
    }
}
