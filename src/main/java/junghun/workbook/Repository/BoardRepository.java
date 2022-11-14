package junghun.workbook.Repository;

import java.util.Optional;
import junghun.workbook.Repository.search.BoardSearch;
import junghun.workbook.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @EntityGraph(attributePaths = {"imageSet"}) // board를 조회한후 image를 다시 select 하기때문에 이 어노테이션으로 한 세션에 한번에 조회할 수 있도록 함
    @Query("select b from Board b where b.bno=:bno")
    Optional<Board> findByIdWithImage(@Param("bno")Long bno);

}
