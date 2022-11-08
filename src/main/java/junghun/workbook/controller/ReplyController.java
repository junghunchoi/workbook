package junghun.workbook.controller;


import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import junghun.workbook.dto.PageRequestDTO;
import junghun.workbook.dto.PageResponseDTO;
import junghun.workbook.dto.ReplyDTO;
import junghun.workbook.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // 메소드의 모든 리턴값은 xml 또는 json으로 반환된다.
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor // 의존성 주입을 위함
public class ReplyController
{

    private final ReplyService replyService;

    @ApiOperation(value = "Replies POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(
            @Valid @RequestBody ReplyDTO replyDTO ,
            BindingResult bindingResult) throws BindException {

        log.info(replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();

        Long rno = replyService.register(replyDTO);

        resultMap.put("rno" , rno);

        return resultMap;
    }

    @ApiOperation(value = "Replies of Board", notes = "GET 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno , // 경로의 파라미터를 bno에 셋팅한다는 뜻
                                             PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getListOfBoard(bno ,
                pageRequestDTO);

        log.info("pageResponseDTO........" + pageResponseDTO);

        return pageResponseDTO;

    }

    @ApiOperation(value = "Read Replies", notes = "GET 방식으로 특정 댓글조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);

        return replyDTO;
    }

    @ApiOperation(value = "Delete Replies", notes = "DELETE 방식으로 댓글 삭제")
    @DeleteMapping(value = "{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno" , rno);

        return resultMap;

    }

}
