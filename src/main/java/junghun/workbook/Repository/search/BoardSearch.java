package junghun.workbook.Repository.search;

import junghun.workbook.dto.BoardListAllDTO;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword,
        Pageable pageable);

        Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);


}
