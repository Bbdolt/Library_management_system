package top.cengweiye.library.repository;
import top.cengweiye.library.domain.Reader; // 导入Reader实体类
import org.springframework.data.jpa.repository.JpaRepository; // 导入JpaRepository接口
import org.springframework.stereotype.Repository; // 导入@Repository注解

// 使用@Repository注解标记该接口为一个Spring Data JPA的仓库接口
@Repository
public interface ReaderDao extends JpaRepository<Reader, Long> {
    //此方法遵循Spring Data JPA的命名约定，Spring Data JPA会根据方法名自动生成查询。
    Reader findByName(String name);
    //此方法遵循Spring Data JPA的命名约定，Spring Data JPA会根据方法名自动生成查询。
    Reader findByNameAndPassword(String name, String password);
    Reader findByBorrowBookId(int borrowBookId);
}