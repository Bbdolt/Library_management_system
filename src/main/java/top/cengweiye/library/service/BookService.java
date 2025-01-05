package top.cengweiye.library.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import top.cengweiye.library.domain.Book;

public interface BookService {
    public Page<Book> getBooks(int page, int size);

    public Page<Book> getBooklikes(int page, int size, String keyWord);
    @Transactional
    public void returnBook(Integer bookId);
}
