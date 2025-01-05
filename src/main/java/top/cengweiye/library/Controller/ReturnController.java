package top.cengweiye.library.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cengweiye.library.Utilities.Result;
import top.cengweiye.library.domain.ReturnRequest;
import top.cengweiye.library.repository.BorrowDao;
import top.cengweiye.library.service.BookService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/return")
public class ReturnController {
    @Resource
    private BookService bookService;
    @Resource
    private BorrowDao borrowDao;

    @PostMapping
    @Transactional    // 进行事务管理
    public Result<String> returnBook(@RequestBody ReturnRequest returnRequest){

        borrowDao.deleteByBookId(returnRequest.getBookId());
        bookService.returnBook(returnRequest.getBookId());
        return Result.success("还书成功");
    }
}