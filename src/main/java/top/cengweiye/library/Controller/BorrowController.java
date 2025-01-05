package top.cengweiye.library.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Blacklist;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.BlacklistDao;
import top.cengweiye.library.repository.ReaderDao;
import top.cengweiye.library.service.BorrowService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Resource
    private BorrowService BorrowService;
    @Resource
    private ReaderDao ReaderDao;

    @Resource
    private BlacklistDao blacklistDao;

    @PostMapping
    public ResponseEntity<Map<String, Object>> borrowBook(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Integer bookId = (Integer) requestBody.get("bookId");
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
        Map<String, Object> response = new HashMap<>();
        Reader reader = ReaderDao.findByName(name);
        Optional<Blacklist> blacklist = blacklistDao.findById(reader.getBorrowBookId());
        if (blacklist.isPresent()){
            response.put("success", false);
            response.put("message", "您已被封禁，请联系管理员解封！");
            return ResponseEntity.ok(response);
        }
        int borrowBookId = reader.getBorrowBookId();

        try {
            boolean success = BorrowService.borrowBook(bookId, borrowBookId);
            if (success) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "该书已经被借阅！");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "发生错误：" + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
