package junghun.workbook.Repository;

import junghun.workbook.Repository.Search.BoardSearch;
import junghun.workbook.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}
