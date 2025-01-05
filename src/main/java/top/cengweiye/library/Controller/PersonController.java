package top.cengweiye.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Book;
import top.cengweiye.library.domain.Borrow;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.ReaderDao;
import top.cengweiye.library.service.BorrowService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonController {

    @Resource
    private BorrowService borrowService;
    @Resource
    private ReaderDao readerDao;

    @GetMapping("/person")
    public String getperson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model, HttpServletRequest request) {
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (token != null) {
                        token = token.substring(6);
                        if (TokenUtil.verify(token)) {
                            name = TokenUtil.getUsernameFromToken(token);
                        }
                    }
                }
            }
        }
        if (name != null) {
            // 获取用户信息
            Reader reader = readerDao.findByName(name);
            if (reader != null) {
                model.addAttribute("reader", reader);
                int borrowBookId = reader.getBorrowBookId();

                // 获取用户的借阅记录
                Page<Borrow> borrowPage = borrowService.getBorrow(borrowBookId, page, size);
                model.addAttribute("borrowPage", borrowPage);
            }
        }

        return "person";
    }
}
