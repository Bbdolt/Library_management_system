package top.cengweiye.library.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cengweiye.library.Utilities.GetToken;
import top.cengweiye.library.Utilities.Result;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.service.UpdatePersonService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Resource
    private UpdatePersonService updatePersonService;

    @PostMapping
    public Result<String> update(@RequestBody Reader newreader, HttpServletRequest request, HttpServletResponse response){
        String token = GetToken.getToken(request);
        String name = updatePersonService.updatePerson(newreader, token);
        String token_twice = TokenUtil.generateToken(name);
        Cookie cookie = new Cookie("Authorization","Bearer" + token_twice);
        cookie.setHttpOnly(true);  // 防止客户端 JavaScript 访问，提高安全性
        cookie.setSecure(true);    // 仅在 HTTPS 下传输（生产环境中应启用）
        cookie.setPath("/");       // 设置路径为根目录，确保整个应用都能访问
        cookie.setMaxAge(60 * 60 * 24 * 7);  // 设置过期时间为 7 天（根据需要调整）
        response.addCookie(cookie);
        return Result.success("更新成功");
    }
}
