package junghun.workbook.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
	/*
		업로드된 파일의 UUID 값, 파일이름, 이미지여부를 객체로 구성하고 getLink()를 통해서 첨부파일의 경로 처리하기 위한 클래스
	 */

    private String uuid;

    private String fileName;

    private boolean img;


    // 첨부파일  경로 , 동적으로 값을 셋팅하려면 dto에 해당 파라미터를 설정함
    public String getLink() {

        if (img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return uuid + "_" + fileName;
        }
    }
}
