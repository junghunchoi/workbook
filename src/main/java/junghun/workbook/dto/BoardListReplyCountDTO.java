package junghun.workbook.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Data
public class BoardListReplyCountDTO {

    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;

    private Long replyCount;
}
