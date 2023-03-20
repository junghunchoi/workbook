package junghun.workbook.service;


import junghun.workbook.dto.BoardDTO;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.dto.BoardListReplyLikeCountDTO;
import junghun.workbook.dto.PageRequestDTO;
import junghun.workbook.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    Long thumbsup(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyLikeCountDTO> listWithReplyLikeCount(PageRequestDTO pageRequestDTO);


}

