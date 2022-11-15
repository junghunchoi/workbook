package junghun.workbook.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {// @onetomany  처리에서 순번에 맞게 정리하기 위함

    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Board board;


    // 부모의 상태에 따라 변화해야하므로 아래와 같이 설정한다.
    @Override
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Board board){
        this.board = board;
    }

}