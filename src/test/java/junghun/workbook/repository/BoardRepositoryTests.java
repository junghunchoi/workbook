package junghun.workbook.repository;


import junghun.workbook.Repository.BoardRepository;
import junghun.workbook.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
}
