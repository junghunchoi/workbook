package junghun.workbook.controller;

import junghun.workbook.Repository.MemberRepository;
import junghun.workbook.dto.MemberJoinDTO;
import junghun.workbook.service.MemberSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberSevice memberSevice;

	@GetMapping("/login")
	public void loginGet(String error, String logout) {
		/*
		문제가 생길경우나 로그아웃할 때 처리하기 위함
		 */
	}

	@GetMapping("/join")
	public void joinGet() {
		log.info("join get....");

	}

	@PostMapping("/join")
	public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
		try {
			memberSevice.join(memberJoinDTO);
		} catch (MemberSevice.MidExistsException e) {
			redirectAttributes.addFlashAttribute("error", "mid");
			return "redirect:/member/join";
		}
		redirectAttributes.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
}
