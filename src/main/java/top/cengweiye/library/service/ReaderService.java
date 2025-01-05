package top.cengweiye.library.service;
import org.springframework.data.domain.Page;
import top.cengweiye.library.domain.Reader;

public interface ReaderService {
    Reader loginService(String name, String password);
    //注册服务
    Reader registService(Reader reader);
    Page<Reader> getReaderList(int pageNum, int pageSize);
}