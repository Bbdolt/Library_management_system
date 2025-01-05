package top.cengweiye.library.Utilities;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import top.cengweiye.library.domain.Blacklist;
import top.cengweiye.library.domain.Borrow;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.BlacklistDao;
import top.cengweiye.library.repository.BorrowDao;
import top.cengweiye.library.repository.ReaderDao;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@EnableScheduling
public class CheckBlackList {
    @Resource
    private BorrowDao borrowDao;

    @Resource
    private BlacklistDao blacklistDao;

    @Resource
    private ReaderDao readerDao;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // 每天午夜 12 点执行一次
    public void checkOverdueAndBlacklist() {
        LocalDateTime currentDate = LocalDateTime.now();
        // 获取所有未归还的借阅记录
        List<Borrow> overdueBorrows = borrowDao.findAllOverdueBorrows(currentDate);

        for (Borrow borrow : overdueBorrows) {
            // 检查该借阅者是否已在黑名单中
            Optional<Blacklist> existingBlacklist = blacklistDao.findById(borrow.getBorrowBookId());
            if (!existingBlacklist.isPresent()) {
                // 添加借阅者到黑名单
                Blacklist blacklist = new Blacklist();
                blacklist.setBorrowBookId(borrow.getBorrowBookId());
                Reader reader = readerDao.findByBorrowBookId(borrow.getBorrowBookId());
                blacklist.setName(reader.getName());  // 获取借阅者的名字
                blacklist.setReason("借阅逾期未归还");
                blacklistDao.save(blacklist);

                // 你也可以选择在这里发送提醒或者邮件通知借阅者
            }
        }
    }
}
