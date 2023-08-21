package junghun.workbook.service;


import java.util.Optional;
import junghun.workbook.dto.BoardDTO;
import junghun.workbook.dto.PageRequestDTO;
import junghun.workbook.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

//    @Test
//    public void testRegister() {
//
//        log.info(boardService.getClass().getName());
//
//        BoardDTO boardDTO = BoardDTO.builder()
//                .title("Sample Title...")
//                .content("Sample Content...")
//                .writer("user00")
//                .build();
//
//        Long bno = boardService.register(boardDTO);
//
//        log.info("bno: " + bno);
//    }

    @Test
    public void testModify() {

        //변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(1L)
                .title("씨부레")
                .content("Updated content 101...")
            .thumb(2L)
                .build();

        boardService.modify(boardDTO);

    }

    @Test
    public void testList() {

        //제목 혹은 내용 혹은 작성자가 1이라는 문자열을 가진 데이터를 검색하고 페이징 처리
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

    }

//    @Test
//    public void Thumbsup() {
//        boardService.thumbsup(1L);
//    }

//    @Test
//    public void testReadAll() {
//        Long bno = 101l;
//
//        BoardDTO boardDTO = boardService.readOne(bno);
//
//        log.info(boardDTO);
//
//        for (String fileName : boardDTO.getFileNames()) {
//            log.info(fileName);
//        }
//    }


}
