package top.cengweiye.library.service.serviceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.DigestUtils;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.ReaderDao;
import top.cengweiye.library.service.ReaderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Resource
    private ReaderDao readerDao;
    @Override
    public Reader loginService(String name, String password) {
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        String en_password = DigestUtils.md5DigestAsHex(password.getBytes());
        Reader reader = readerDao.findByNameAndPassword(name, en_password);
//        if(reader != null){
//            reader.setPassword("");
//        }
//        return reader;
        return readerDao.findByNameAndPassword(name, en_password);
    }
    @Override
    public Reader registService(Reader reader) {
        //当新用户的用户名已存在时
        if(readerDao.findByName(reader.getName())!=null){
            // 无法注册
            return null;
        }else{
            //返回创建好的用户对象(带uid)
            String passwd = reader.getPassword();
            String encryptPasswd = DigestUtils.md5DigestAsHex(passwd.getBytes());
            reader.setPassword(encryptPasswd);
            return readerDao.save(reader);
        }
    }
    @Override
    public Page<Reader> getReaderList(int pageNum, int pageSize){
        return readerDao.findAll(PageRequest.of(pageNum, pageSize));
    }
}
