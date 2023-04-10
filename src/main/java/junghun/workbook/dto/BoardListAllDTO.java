package junghun.workbook.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListAllDTO {
    // 화면설계할 떄 필요한 데이터를 셋팅한다.
    // board의 기본적인 정보와 reply의 갯수 및 image가 셋팅되어야한다.
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long replyCount;

    private List<BoardImageDTO> boardImages;
}
