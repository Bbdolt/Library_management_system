package top.cengweiye.library.service.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.cengweiye.library.domain.Book;
import top.cengweiye.library.domain.Borrow;
import top.cengweiye.library.repository.BookDao;
import top.cengweiye.library.repository.BorrowDao;
import top.cengweiye.library.service.BorrowService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BookDao BookDao;
    @Resource
    private BorrowDao BorrowDao;
    @Override
    public boolean borrowBook(Integer bookId,Integer borrowBookId) {
        Optional<Book> optionalBook = BookDao.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if ("可借阅".equals(book.getStatus())) {
                // 更新书籍状态为 "已借阅"
                book.setStatus("已借阅");
                BookDao.save(book);

                // 向 borrow 表中插入记录
                Borrow borrow = new Borrow();
                borrow.setBorrowBookId(borrowBookId);
                borrow.setBookId(bookId);
                borrow.setBorrowDate(LocalDateTime.now());
                borrow.setReturnDate(LocalDateTime.now().plusDays(30)); // 假设借阅期为30天
                BorrowDao.save(borrow);

                return true;
            }
        }
        return false;
    }

    @Override
    public Page<Borrow> getBorrow(int borrowbookid, int page, int size) { return BorrowDao.findByBorrowBookId(borrowbookid,PageRequest.of(page, size));}
}
