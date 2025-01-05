package top.cengweiye.library.domain;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Column(name= "name")
    private String name;

    @Column(name= "op_time")
    private LocalDateTime opTime;

    @Column(name= "op")
    private String op;

    @Column(name= "ip")
    private String ip;

    @Column(name= "dev_info")
    private String devInfo;

    public Log(String ipAddress, String userAgent, LocalDateTime currentDateTime, String name, String op) {
        this.ip = ipAddress;
        this.devInfo = userAgent;
        this.opTime = currentDateTime;
        this.name = name;
        this.op = op;
    }

    public Log() {

    }


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getOpTime() {
        return opTime;
    }

    public void setOpTime(LocalDateTime opTime) {
        this.opTime = opTime;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(String devInfo) {
        this.devInfo = devInfo;
    }
}
