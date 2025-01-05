package top.cengweiye.library.domain;
import javax.persistence.*;

@Entity
@Table(name = "bookrack")
public class Bookrack {

    @Id
    @Column(name = "bookrack_id")
    private int bookrackId;

    @Column(name = "room_id")
    private int roomId;

    public void setBookrackId(int bookrackId) {
        this.bookrackId = bookrackId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getBookrackId() {
        return bookrackId;
    }
}
