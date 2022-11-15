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
