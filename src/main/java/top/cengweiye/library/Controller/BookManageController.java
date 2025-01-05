package top.cengweiye.library.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cengweiye.library.Utilities.GetInfo;
import top.cengweiye.library.Utilities.GetToken;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Book;
import top.cengweiye.library.repository.LogDao;
import top.cengweiye.library.service.BookService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import top.cengweiye.library.domain.Log;

@Controller
@RequestMapping("/bookmanage")
public class BookManageController {

    @Resource
    private BookService bookService;

    @Resource
    private LogDao logDao;

    @GetMapping
    public String getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model, HttpServletRequest request) {
        String token = GetToken.getToken(request);
        String name = TokenUtil.getUsernameFromToken(token);

        // 记录日志
        String ipAddress = GetInfo.getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String op = "";

        if (!name.equals("admin")) {
            op = "非法访问管理页面";
            Log log = new Log(ipAddress, userAgent, currentDateTime, name, op);
            logDao.save(log);
            return "redirect:/login.html";
        }

        Page<Book> bookPage = bookService.getBooks(page, size);
        model.addAttribute("bookPage", bookPage);
        return "bookmanage";
    }
}
