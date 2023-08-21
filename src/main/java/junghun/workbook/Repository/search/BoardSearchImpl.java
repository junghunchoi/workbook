package junghun.workbook.Repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import junghun.workbook.dto.BoardImageDTO;
import junghun.workbook.dto.BoardListAllDTO;
import junghun.workbook.dto.BoardListReplyCountDTO;
import junghun.workbook.dto.BoardListReplyLikeCountDTO;
import junghun.workbook.entity.Board;
import junghun.workbook.entity.QBoard;
import junghun.workbook.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;


//QuerydslRepositorySupport를 상속해야 querydsl을 사용할 수 있
@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

//	@Override
//	public Page<Board> search1(Pageable pageable) {
//
//		QBoard board = QBoard.board; // 어떤 Entity를 테이블로 가져올지 정한다.
//
//		JPQLQuery<Board> query = from(board); //from 절을 셋팅한다.
//
//		BooleanBuilder booleanBuilder = new BooleanBuilder(); // ( where 문을 생성하는 객체
//
//		booleanBuilder.or(board.title.contains("뭘까")); // title like ...
//
//		booleanBuilder.or(board.content.contains("11")); // content like ....
//
//		query.where(booleanBuilder); //booleanBuilder를 통해 생성된 where문을 where절에 셋팅한다.
//		query.where(board.bno.gt(0L)); //bno가 0보다 클 때
//
//		//paging처리를 위한 내용을 쿼리에 적용시킨다.
//		this.getQuerydsl().applyPagination(pageable, query);
//
//		List<Board> list = query.fetch();
//
//		long count = query.fetchCount();
//
//		return null;
//	}



	//	@Override
//	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
//
//		QBoard board = QBoard.board;
//		JPQLQuery<Board> query = from(board);
//
//		if ((types != null && types.length > 0) && keyword != null) { //검색 조건과 키워드가 있다면
//
//			BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
//
//			for (String type : types) {
//
//				switch (type) {
//					case "t":
//						booleanBuilder.or(board.title.contains(keyword));
//						break;
//					case "c":
//						booleanBuilder.or(board.content.contains(keyword));
//						break;
//					case "w":
//						booleanBuilder.or(board.writer.contains(keyword));
//						break;
//				}
//			}//end for
//			query.where(booleanBuilder);
//		}//end if
//
//		//bno > 0
//		query.where(board.bno.gt(0L));
//
//		//paging
//		this.getQuerydsl().applyPagination(pageable, query); // querydsl 내 pagination을 지원하는 함수
//
//		List<Board> list = query.fetch();
//
//		long count = query.fetchCount();
//
//		return new PageImpl<>(list, pageable, count);
//	}
//
	@Override
	public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword,
	                                                         Pageable pageable) {

		QBoard board = QBoard.board;
		QReply reply = QReply.reply;

		JPQLQuery<Board> query = from(board);
		query.leftJoin(reply).on(reply.board.eq(board)); // on절을 지정

		query.groupBy(board);

		if (types != null && types.length > 0 && keyword != null) {
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
			}
			query.where(booleanBuilder);
		}

		query.where(board.bno.gt(0L));

		// JPQL의 결과를 바로 dto로 처리하는 기능
		//Projections -> 가져오고자 하는 필드만 셋팅하는 클래스, field 명이 반드시 일치해야한다.
		JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(
				Projections.bean(BoardListReplyCountDTO.class, board.bno, board.title, board.writer,
						board.regDate, reply.count().as("replyCount")));

		this.getQuerydsl().applyPagination(pageable, dtoQuery);

		List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

		Long count = dtoQuery.fetchCount();

		return new PageImpl<>(dtoList, pageable, count); // 댓글, 페이지를 처리하는 내장함수
	}

	@Override
	public Page<BoardListReplyLikeCountDTO> searchWithReplyLikeCount(String[] types, String keyword, Pageable pageable) {
		return null;
	}

	@Override
	public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword,
	                                           Pageable pageable) {

		QBoard board = QBoard.board;
		QReply reply = QReply.reply;

		// 게시물 조회 후 바로 이미지를 조회하는 쿼리 실행
		JPQLQuery<Board> boardJPQLQuery = from(board);
		boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));

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
						break;
				}
			}
		}

		boardJPQLQuery.groupBy(board);


		getQuerydsl().applyPagination(pageable, boardJPQLQuery);// paging

		JPQLQuery<Tuple> tupleList = boardJPQLQuery.select(board, reply.countDistinct());


		List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {
			Board board1 = (Board) tuple.get(board);
			long replyCount = tuple.get(1, Long.class);

			BoardListAllDTO dto = BoardListAllDTO.builder()
					.bno(board1.getBno())
					.title(board1.getTitle())
					.writer(board1.getWriter())
					.regDate(board1.getRegDate())
					.replyCount(replyCount)
					.build();


			// BoardImage를 dto로 처리하는 부분
			List<BoardImageDTO> imageDTOS = board1.getImageSet().stream().sorted().map(boardImage -> BoardImageDTO.builder()
					.uuid(boardImage.getUuid())
					.fileName(boardImage.getFileName())
					.ord(boardImage.getOrd())
					.build()
			).collect(Collectors.toList());

			dto.setBoardImages(imageDTOS);

			return dto;
		}).collect(Collectors.toList());

		long totalCount = boardJPQLQuery.fetchCount();

		return new PageImpl<>(dtoList, pageable, totalCount);
	}
}

