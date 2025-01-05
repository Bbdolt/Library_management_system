package top.cengweiye.library.service.serviceImpl;

import org.springframework.stereotype.Service;
import top.cengweiye.library.Utilities.GetToken;
import top.cengweiye.library.Utilities.TokenBlacklist;
import top.cengweiye.library.Utilities.TokenUtil;
import top.cengweiye.library.domain.Reader;
import top.cengweiye.library.repository.ReaderDao;
import top.cengweiye.library.service.UpdatePersonService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UpdatePersonServiceImpl implements UpdatePersonService {
    @Resource
    private ReaderDao readerdao;

    @Override
    public String updatePerson(Reader reader, String token) {
        String name = TokenUtil.getUsernameFromToken(token);
        TokenBlacklist.blacklistToken(token);
        String update_name = reader.getName();
        int update_age = reader.getAge();
        String update_sex = reader.getSex();
        String update_address = reader.getAddress();
        Reader old = readerdao.findByName(name);
        old.setAddress(update_address);
        old.setAge(update_age);
        old.setSex(update_sex);
        old.setName(update_name);
        readerdao.save(old);
        return old.getName();
    }
}
