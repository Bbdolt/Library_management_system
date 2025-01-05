package top.cengweiye.library.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cengweiye.library.Utilities.Result;
import top.cengweiye.library.domain.ReturnRequest;
import top.cengweiye.library.repository.BookDao;
import top.cengweiye.library.repository.BorrowDao;

import javax.annotation.Resource;

@RestController
@RequestMapping("/deletebook")
public class DeleteBookController {
    @Resource
    private BookDao bookDao;

    @Resource
    private BorrowDao borrowDao;

    @Transactional
    @PostMapping
    public Result<String> deleteBook(@RequestBody ReturnRequest returnRequest) {
        int bookId = returnRequest.getBookId();
        borrowDao.deleteByBookId(bookId);
        bookDao.deleteByBookId(bookId);
        return Result.success("删除成功");
    }
}
