package top.cengweiye.library.domain;
import javax.persistence.*;

@Entity
@Table(name = "blacklist")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_book_id")
    private int borrowBookId;

    @Column(name = "name")
    private String name;

    @Column(name = "reason")
    private String reason;

    public int getBorrowBookId() {
        return borrowBookId;
    }

    public void setBorrowBookId(int borrowBookId) {
        this.borrowBookId = borrowBookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
