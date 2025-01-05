package top.cengweiye.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.cengweiye.library.domain.Log;

@Repository
public interface LogDao extends JpaRepository<Log, Long> {

}
