package junghun.workbook.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BoardListReplyLikeCountDTO {

    private Long Bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;

    private Long replyCount;
    private Long thumb;

}
