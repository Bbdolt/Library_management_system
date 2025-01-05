package top.cengweiye.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.cengweiye.library.domain.Borrow;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowDao extends JpaRepository<Borrow, Integer> {
    Page<Borrow> findByBorrowBookId(int borrowBookId, Pageable pageable);
    void deleteByBookId(Integer bookId);

    @Query("SELECT b FROM Borrow b WHERE b.returnDate < :currentDate AND b.bookId IS NOT NULL")
    List<Borrow> findAllOverdueBorrows(@Param("currentDate") LocalDateTime currentDate);
}
