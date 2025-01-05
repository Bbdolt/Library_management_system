package top.cengweiye.library.Controller;

        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import top.cengweiye.library.Utilities.Result;
        import top.cengweiye.library.domain.Book;
        import top.cengweiye.library.service.EditBookService;

        import javax.annotation.Resource;

@RestController
@RequestMapping("/editbook")
public class EditBookController {
    @Resource
    private EditBookService editBookService;

    @PostMapping
    public Result<String> editBook(@RequestBody Book book){
        String data = editBookService.editBook(book);
        return Result.success(data);
    }

    @PostMapping("/add")
    public Result<String> addBook(@RequestBody Book book){
        String data = editBookService.addBook(book);
        return Result.success(data);
    }
}
