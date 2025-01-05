package top.cengweiye.library.service.serviceImpl;

import org.springframework.stereotype.Service;
import top.cengweiye.library.domain.Book;
import top.cengweiye.library.repository.BookDao;
import top.cengweiye.library.service.EditBookService;

import javax.annotation.Resource;

@Service
public class EditBookServiceImpl implements EditBookService {
    @Resource
    private BookDao bookDao;

    @Override
    public String addBook(Book book){
        if(bookDao.findByBookId(book.getBookId()) != null){
            return "该书已存在";
        }
        book.setStatus("可借阅");
        bookDao.save(book);
        return "添加成功";
    }

    @Override
    public String editBook(Book book){
        book.setStatus("可借阅");
        bookDao.save(book);
        return "修改成功";
    }
}
