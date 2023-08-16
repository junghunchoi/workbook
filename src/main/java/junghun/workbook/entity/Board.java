package junghun.workbook.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import lombok.*;

import org.hibernate.annotations.BatchSize;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "imageSet") // imageset은 tostring 에서 제외한다.
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }







    @OneToMany(mappedBy = "board", // mapedby를 안하면 맵핑 테이블이 생성되나 자식관계에 있는 테이블을 생성하면 관용적으로 붙인다.
        cascade = {CascadeType.ALL},
        fetch = FetchType.LAZY,
        orphanRemoval = true) // 기존의 첨부파일을 모두 삭제한 후 새로 추가한 첨부파일을 엎어친다.
    @Builder.Default
    @BatchSize(size = 20) // 게시물 조회할 경우 batch로 조회할 수 있게 어노테이션 셋팅 , db 과부하를 줄이기 위함
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){

        BoardImage boardImage = BoardImage.builder()
            .uuid(uuid)
            .fileName(fileName)
            .board(this)
            .ord(imageSet.size())
            .build();

        imageSet.add(boardImage);
    }

    public void clearImages() {

        imageSet.forEach(boardImage -> boardImage.changeBoard(null));

        this.imageSet.clear();
    }

}