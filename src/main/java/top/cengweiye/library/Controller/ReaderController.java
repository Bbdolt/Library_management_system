package top.cengweiye.library.Controller;
import org.springframework.web.bind.annotation.*;
import top.cengweiye.library.Utilities.Result;
import top.cengweiye.library.Utilities.TokenBlacklist;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.LogDao;
import top.cengweiye.library.service.ReaderService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import top.cengweiye.library.Utilities.GetInfo;
import top.cengweiye.library.domain.Log;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Resource
    private ReaderService readerService;

    @Resource
    LogDao logDao;

    @PostMapping("/login")
    public Result<Reader> loginController(@RequestBody Reader newUser,HttpServletResponse response,HttpServletRequest request){
        // 获取信息
        String ipAddress = GetInfo.getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String name = newUser.getName();
        String op = "";

        Reader user = readerService.loginService(newUser.getName(), newUser.getPassword());
        if(user!=null){
            String token = TokenUtil.generateToken(user.getName());
            // 返回Token给前端，可以通过Cookie或响应头设置
            Cookie cookie = new Cookie("Authorization","Bearer" + token);
            cookie.setHttpOnly(true);  // 防止客户端 JavaScript 访问，提高安全性
            cookie.setSecure(true);    // 仅在 HTTPS 下传输（生产环境中应启用）
            cookie.setPath("/");       // 设置路径为根目录，确保整个应用都能访问
            cookie.setMaxAge(60 * 60 * 24 * 7);  // 设置过期时间为 7 天（根据需要调整）

            // 将 Cookie 添加到响应中
            response.addCookie(cookie);
            // 记录日志
            op = "登录成功";
            Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
            logDao.save(log);
            return Result.success(user, "登录成功！");

        }else{
            op = "登录失败";
            Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
            logDao.save(log);
            return Result.error("401","账号或密码错误！");
        }
    }
    @PostMapping("/register")
    public Result<Reader> registController(@RequestBody Reader newUser, HttpServletRequest request){
        String ipAddress = GetInfo.getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String name = newUser.getName();
        String op = "";

        Reader user = readerService.registService(newUser);
        if(user!=null){
            op = "注册成功";
            Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
            logDao.save(log);
            return Result.success(user,"注册成功！");
        }else{
            op = "注册失败";
            Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
            logDao.save(log);
            return Result.error("409","用户名已存在！");
        }
    }
    @PostMapping("/logout")
    public Result<String> logoutController(HttpServletRequest request, HttpServletResponse response) {
        // 获取信息
        String ipAddress = GetInfo.getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String name = "";
        String op = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (token != null) {
                        token = token.substring(6);
                        name = TokenUtil.getUsernameFromToken(token);
                        if (TokenUtil.verify(token)) {
                            TokenBlacklist.blacklistToken(token);
                        }
                    }
                }
            }
        }
        op = "注销";
        Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
        logDao.save(log);
        return Result.success("成功退出登录！");
    }
}
