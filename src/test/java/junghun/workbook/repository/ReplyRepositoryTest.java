//package junghun.workbook.repository;
//
//
//import junghun.workbook.Repository.BoardRepository;
//import junghun.workbook.Repository.ReplyRepository;
//import junghun.workbook.dto.BoardListReplyCountDTO;
//import junghun.workbook.entity.Board;
//import junghun.workbook.entity.Reply;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import javax.transaction.Transactional;
//
//
//@SpringBootTest
//@Log4j2
//public class ReplyRepositoryTest {
//
//    @Autowired
//    private ReplyRepository replyRepository;
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    public void testInsert() {
//
//        //실제 DB에 있는 bno
//        Long bno  = 4L;
//
//        Board board = Board.builder().bno(bno).build();
//
//        Reply reply = Reply.builder()
//                .board(board)
//                .replyText("댓글.....")
//                .replyer("replyer1")
//                .build();
//
//        replyRepository.save(reply);
//
//    }
//
//    @Transactional
//    @Test
//    public void testSearchReplyCount() {
//
//        String[] types = {"t" , "c" , "w"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0 , 10 , Sort.by("bno").descending());
//
//        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types , keyword , pageable);
//
//        log.info(result.getTotalPages());
//        log.info(result.getSize());
//        log.info(result.getNumber());
//        log.info(result.hasPrevious());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
//
//
//
//}
