package junghun.workbook.repository;


import java.util.UUID;
import javax.transaction.Transactional;
import junghun.workbook.Repository.BoardRepository;

import junghun.workbook.entity.Board;
import junghun.workbook.entity.BoardImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){
        IntStream.range(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .writer("user"+(i%10))
                    .build();
            Board result = boardRepository.save((board));
//            log.info("bno:"+result.getBno());
                });
    }

    @Test
    public void testSelect(){
        Long bno = 150L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info("LOG....." + board);



    }


    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count:" + result.getTotalElements());
    }

    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.search1(pageable);

    }

    @Test
    public void testSearchAll2(){
        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());

        result.getContent().forEach(board-> log.info(board));
    }




//    @Test
//    public void testInserWithImages() {
//        Board board = Board.builder()
//            .title("ImageTest")
//            .content("첨부파일테스트")
//            .writer("tester")
//            .build();
//
//        for (int i = 0; i < 3; i++) {
//            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
//
//        }
//        boardRepository.save(board);
//    }

//    @Test
//    public void testReadWithImages() {
//
//        Optional<Board> result = boardRepository.findByIdWithImage(1l);
//
//        Board board = result.orElseThrow();
//
//        log.info(board);
//        for (BoardImage boardImage : board.getImageSet()) {
//            log.info("----------boardImage" + boardImage);
//        }
//
//    }


//    @Test
//    public void testInsertAll() {
//
//        for (int i = 1; i <= 100; i++) {
//
//            Board board  = Board.builder()
//                .title("Title.."+i)
//                .content("Content.." + i)
//                .writer("writer.." + i)
//                .build();
//
//            for (int j = 0; j < 3; j++) {
//
//                if(i % 5 == 0){
//                    continue;
//                }
//                board.addImage(UUID.randomUUID().toString(),i+"file"+j+".jpg");
//            }
//            boardRepository.save(board);
//
//        }//end for
//    }
//
//    @Transactional
//    @Test
//    public void testSearchImageReplyCount() {
//
//        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
//
//        //boardRepository.searchWithAll(null, null,pageable);
//
//        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null,null,pageable);
//
//        log.info("---------------------------");
//        log.info(result.getTotalElements());
//
//        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
//
//
//    }
}
