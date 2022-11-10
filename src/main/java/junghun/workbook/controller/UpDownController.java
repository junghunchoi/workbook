package junghun.workbook.controller;


import io.swagger.annotations.ApiOperation;
import junghun.workbook.dto.upload.UpLoadFileDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

	@Value("${junghun.workbook.upload.path}")// application.propert에 설정된 파일 경로
	private String uploadPath;

	@ApiOperation(value = "Upload Post", notes = "Post 방식으로 파일 등록")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String upload(UpLoadFileDTO upLoadFileDTO) {
		log.info(upLoadFileDTO);

		if (upLoadFileDTO.getFiles() != null) {
			upLoadFileDTO.getFiles().forEach(multipartFile -> {

				String orginalName = multipartFile.getOriginalFilename();

				log.info(orginalName);

				String uuid = UUID.randomUUID().toString();

				Path savePath = Paths.get(uploadPath, uuid + "_" + orginalName);

				try {
					multipartFile.transferTo(savePath);

					if (Files.probeContentType(savePath).startsWith("image")) {

						File thumbFile = new File(uploadPath, "s_");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		}

		return null;
	}

}
