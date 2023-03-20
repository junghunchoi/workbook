package junghun.workbook.Repository.Search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.dto.BoardListReplyLikeCountDTO;
import junghun.workbook.entity.QReply;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import junghun.workbook.entity.Board;
import junghun.workbook.entity.QBoard;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{


    public BoardSearchImpl() { // querydsl을 사용하기 위해 super로 사용하고자하는 클래스를 넣어둔다.

        super(Board.class);
    }


    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
                query.where(booleanBuilder);
            }
            query.where(board.bno.gt(0L));
        }
        //PAGING 처리
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword,
        Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0l));


        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(
            Projections.bean(BoardListReplyCountDTO.class, board.bno, board.title, board.writer,
                board.regDate, reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }

    /////// LIKE 추가
    @Override
    public Page<BoardListReplyLikeCountDTO> searchWithReplyLikeCount(String[] types, String keyword,
        Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0l));


        JPQLQuery<BoardListReplyLikeCountDTO> dtoQuery = query.select(
            Projections.bean(BoardListReplyLikeCountDTO.class, board.bno, board.title, board.writer,
                board.regDate,board.thumb , reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardListReplyLikeCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);


    }
}
