package junghun.workbook.Repository.Search;

import junghun.workbook.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Pageable;
import junghun.workbook.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword,
        Pageable pageable);

}
