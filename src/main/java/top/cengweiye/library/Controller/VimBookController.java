package top.cengweiye.library.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vimbook")
public class VimBookController {
    @GetMapping
    public String vimbook(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("bookId", bookId);

        return "bookinfo"; // 返回视图名称
    }
}
