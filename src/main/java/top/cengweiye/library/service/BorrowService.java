package top.cengweiye.library.service;

import org.springframework.data.domain.Page;
import top.cengweiye.library.domain.Borrow;

public interface BorrowService {
    public boolean borrowBook(Integer bookId, Integer borrowBookId);

    public Page<Borrow> getBorrow(int borrowbookid, int page, int size);
}
