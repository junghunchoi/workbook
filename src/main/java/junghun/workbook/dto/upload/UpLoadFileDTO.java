package junghun.workbook.dto.upload;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpLoadFileDTO {

    private List<MultipartFile> files;
}
