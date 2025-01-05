package top.cengweiye.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import top.cengweiye.library.domain.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    public Book findByBookId(Integer bookId);
    void deleteByBookId(Integer bookId);
    Page<Book> findByBookNameContaining(String keyWord, Pageable pageable);
}
