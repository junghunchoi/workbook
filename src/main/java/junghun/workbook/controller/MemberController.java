package junghun.workbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	@GetMapping("/login")
	public void loginGet(String error, String logout) {
		/*
		문제가 생길경우나 로그아웃할 때 처리하기 위함
		 */
	}
}
