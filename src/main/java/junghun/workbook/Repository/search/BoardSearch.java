package junghun.workbook.Repository.search;

import junghun.workbook.dto.BoardListAllDTO;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.dto.BoardListReplyLikeCountDTO;
import org.springframework.data.domain.Pageable;
import junghun.workbook.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardSearch {

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword,
        Pageable pageable);

    Page<BoardListReplyLikeCountDTO> searchWithReplyLikeCount(String[] types, String keyword,
        Pageable pageable);



    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
}
