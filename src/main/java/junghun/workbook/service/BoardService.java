package junghun.workbook.service;


import java.util.List;
import java.util.stream.Collectors;
import junghun.workbook.dto.BoardDTO;
import junghun.workbook.dto.BoardListAllDTO;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.dto.BoardListReplyLikeCountDTO;
import junghun.workbook.dto.PageRequestDTO;
import junghun.workbook.dto.PageResponseDTO;
import junghun.workbook.entity.Board;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyLikeCountDTO> listWithReplyLikeCount(PageRequestDTO pageRequestDTO);

    //게시글의 이미지와 댓글의 숫자까지 처리
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);


    //다양한 처리를 하기 위해선 entity로 처리하는것이 좋다.
    default Board dtoToEntity(BoardDTO boardDTO){

        Board board = Board.builder()
                           .bno(boardDTO.getBno())
                           .title(boardDTO.getTitle())
                           .content(boardDTO.getContent())
                           .writer(boardDTO.getWriter())
                           .build();

        if(boardDTO.getFileNames() != null){
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            });
        }
        return board;
    }

    //board 엔티티 객체를 boarddto로 변환처리
    default BoardDTO entityToDTO(Board board) {

        BoardDTO boardDTO = BoardDTO.builder()
                                    .bno(board.getBno())
                                    .title(board.getTitle())
                                    .content(board.getContent())
                                    .writer(board.getWriter())
                                    .regDate(board.getRegDate())
                                    .modDate(board.getModDate())
                                    .build();

        List<String> fileNames =
            board.getImageSet().stream().sorted().map(boardImage ->
                boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());

        boardDTO.setFileNames(fileNames);

        return boardDTO;
    }


}

