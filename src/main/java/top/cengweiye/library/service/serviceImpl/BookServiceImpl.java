package top.cengweiye.library.service.serviceImpl;

import org.springframework.stereotype.Service;
import top.cengweiye.library.service.BookService;
import top.cengweiye.library.repository.BookDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.cengweiye.library.domain.Book;
import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao BookDao;
    @Override
    public Page<Book> getBooks(int page, int size) {
        return BookDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Book> getBooklikes(int page, int size, String keyWord){
        if (keyWord != null && !keyWord.isEmpty()) {
            return BookDao.findByBookNameContaining(keyWord, PageRequest.of(page, size));
        } else {
            // 如果没有提供搜索条件，则返回所有书籍
            return BookDao.findAll(PageRequest.of(page, size));
        }
    }

    @Override
    public void returnBook(Integer bookId){
        Optional<Book> optionalBook = BookDao.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if ("已借阅".equals(book.getStatus())) {
                book.setStatus("可借阅");
                BookDao.save(book);
            }
        }
    }
}
