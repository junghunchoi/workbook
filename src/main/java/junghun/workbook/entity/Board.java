package junghun.workbook.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.BatchSize;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    //@Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    //@Column(length = 2000, nullable = false)
    private String content;

    //@Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    // 하나의 게시물에 여러개의 첨부파일이 붙을 수 있다는 소리
    @OneToMany(mappedBy = "board",
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