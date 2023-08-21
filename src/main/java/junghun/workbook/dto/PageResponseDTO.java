package junghun.workbook.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Data
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        /*
          시작, 마지막, 데이터에 따른 페이지 갯수 셋팅

         */

        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        //화면에서의 마지막 번호
        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10;

        this.start = this.end - 9;

        //데이터의 개수를 계산한 마지막 페이지 번호
        int last =  (int)(Math.ceil((total/(double)size)));

        this.end = Math.min(end, last);

        this.prev = this.start > 1;

        this.next =  total > this.end * this.size;

    }

}
