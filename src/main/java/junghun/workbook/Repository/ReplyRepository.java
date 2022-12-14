package junghun.workbook.Repository;

import junghun.workbook.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(@Param("bno")Long bno, Pageable pageable);

    // 게시물 삭제 전에 댓글을 먼저 삭제해야한다.
    void deleteByBoard_Bno(Long bno);

}
