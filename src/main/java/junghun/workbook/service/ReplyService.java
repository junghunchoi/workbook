package junghun.workbook.service;

import junghun.workbook.dto.PageRequestDTO;
import junghun.workbook.dto.PageResponseDTO;
import junghun.workbook.dto.ReplyDTO;

public interface ReplyService {

    Long Register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
