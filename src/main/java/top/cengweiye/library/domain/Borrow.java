package top.cengweiye.library.domain;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow")
public class Borrow {
    @Column(name = "borrow_book_id")
    private int borrowBookId;

    @Id
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public int getBorrowBookId() {
        return borrowBookId;
    }

    public void setBorrowBookId(int borrowBookId) {
        this.borrowBookId = borrowBookId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
