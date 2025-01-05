package top.cengweiye.library.Utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetToken {
    public static String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    return cookie.getValue().substring(6);
                }
            }
        }
        return null;
    }
}
