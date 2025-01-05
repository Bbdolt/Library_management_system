package top.cengweiye.library.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name", unique = true)
    private String bookName;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "bookrack_id")
    private int bookrackId;

    @Column(name="status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getBookrackId() {
        return bookrackId;
    }

    public void setBookrackId(int bookrackId) {
        this.bookrackId = bookrackId;
    }
}
