package junghun.workbook.Repository;

import java.util.Optional;
import junghun.workbook.Repository.Search.BoardSearch;
import junghun.workbook.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @EntityGraph(attributePaths = {"imageSet"}) // 자식 테이블을 조회할때 한번의 커넥션에서 데이터를 가져올 수 있도록 함
    @Query("select b from Board b where b.bno = :bno")
    Optional<Board> findByidWithImage(Long bno);
}
