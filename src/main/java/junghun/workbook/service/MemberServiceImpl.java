package junghun.workbook.service;

import junghun.workbook.Repository.MemberRepository;
import junghun.workbook.dto.MemberJoinDTO;
import junghun.workbook.entity.Member;
import junghun.workbook.entity.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberSevice {
	private final ModelMapper modelMapper;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;


	@Override
	public void join(MemberJoinDTO memberJoinDTO) throws MidExistsException {

		String mid = memberJoinDTO.getMid();
		boolean exists = memberRepository.existsById(mid);

		if (exists) {
			throw new MidExistsException();
		}

		Member member = modelMapper.map(memberJoinDTO, Member.class);
		member.changePassword(passwordEncoder.encode(member.getMpw()));
		member.addRole(MemberRole.USER);

		log.info("Member Join -> " + member);
		memberRepository.save(member);

	}
}
