package top.cengweiye.library.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cengweiye.library.domain.Book;
import top.cengweiye.library.service.BookService;

import javax.annotation.Resource;

@Controller
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/")
    public String getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String bookName, // 接收搜索参数
            Model model) {

        Page<Book> bookPage;
        // 如果有搜索关键字，就执行模糊查询，否则显示所有数据
        if (bookName != null && !bookName.isEmpty()) {
            bookPage = bookService.getBooklikes(page, size, bookName); // 执行模糊查询
        } else {
            bookPage = bookService.getBooks(page, size); // 不传 bookName 参数，显示所有数据
        }
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("bookName", bookName); // 将搜索的书名添加到模型中，方便分页时保留搜索条件
        return "index";
    }

}
