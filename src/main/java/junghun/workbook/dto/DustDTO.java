package junghun.workbook.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DustDTO {

    private int seq;


    private int pm10Value;
    private int pm25Value;
    private int pm10Grade;
    private int pm25Grade;

    private Object[] dtoList;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @Builder(builderMethodName = "dto")
    public DustDTO(Object[] dtoList) {

        this.dtoList = dtoList;
    }
}
