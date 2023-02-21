package junghun.api.repository;

import java.util.List;
import junghun.api.Entity.Dust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DustRepository extends JpaRepository<Dust,Long> {

//    List<Dust> selectAll
}
