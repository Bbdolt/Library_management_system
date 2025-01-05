package top.cengweiye.library.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.cengweiye.library.Utilities.Result;
import top.cengweiye.library.domain.Blacklist;
import top.cengweiye.library.repository.BlacklistDao;
import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/usermanage")
public class UsermanageController {

    @Resource
    private BlacklistDao blacklistDao;

    @GetMapping("")
    public String getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Blacklist> blacklistsPage = blacklistDao.findAll(PageRequest.of(page, size));
        model.addAttribute("blacklistsPage", blacklistsPage);
        return "usermanage";
    }

    @Transactional
    @PostMapping("/clear")
    @ResponseBody  // 添加这个注解，表示返回的是一个JSON响应，而不是一个视图
    public Result<String> clear(@RequestBody Map<String, Object> requestData) {
        Integer id = (Integer) requestData.get("id");  // 获取传递的 id
        blacklistDao.deleteByborrowBookId(id);  // 删除黑名单条目
        return Result.success("解封成功");
    }

}
