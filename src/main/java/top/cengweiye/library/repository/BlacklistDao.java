package top.cengweiye.library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.cengweiye.library.domain.Blacklist;
import java.util.Optional;

@Repository
public interface BlacklistDao extends JpaRepository<Blacklist, Integer> {
    Optional<Blacklist> findById(Integer borrowBookId);
    Optional<Blacklist> findByName(String name);
    void deleteByborrowBookId(int borrowBookId);
}
