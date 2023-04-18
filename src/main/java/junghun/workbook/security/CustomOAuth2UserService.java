//package junghun.workbook.security;
//
//
//
//import junghun.workbook.entity.Member;
//import junghun.workbook.entity.MemberRole;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Log4j2
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//	private final MemberRepository memberRepository;
//	private final PasswordEncoder passwordEncoder;
//
//
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//		ClientRegistration clientRegistration = userRequest.getClientRegistration();
//
//		String clientName = clientRegistration.getClientName();
//
//		log.info("NAME: " + clientName);
//
//		OAuth2User oAuth2User = super.loadUser(userRequest);
//
//		Map<String, Object> paramMap = oAuth2User.getAttributes();
//
//		String email = null;
//
//		// 카카오를 비롯한 다른 소셜 로그인을 하기 위해 swith문을 사용
//		switch (clientName) {
//			case "kakao":
//				email = getKakaoEmail(paramMap);
//				break;
//		}
//
//		return generateDTO(email, paramMap);
//	}
//
//	// 이미 회원가입이 된 회원에 대해서는 기존 정보를 반환하고 새롭게 소셜 로그인한 사용자는 자동으로 회원 가입을 처리한다.
//	private MemberSecurityDTO generateDTO(String email, Map<String, Object> params) {
//
//		Optional<Member> result = memberRepository.findByEmail(email);
//
//		//데이터베이스에 해당 이메일을 가진 사용자가 없다면
//		if (result.isEmpty()) {
//			Member member = Member.builder()
//					.mid(email)
//					.mpw(passwordEncoder.encode("1111"))
//					.email(email)
//					.social(true)
//					.build();
//
//			member.addRole(MemberRole.USER);
//			memberRepository.save(member);
//
//			//MemberSecurityDTO 구성 및 반환
//			MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email, "1111", email, false, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//			memberSecurityDTO.setProps(params);
//
//			return memberSecurityDTO;
//		} else {
//			Member member = result.get();
//			MemberSecurityDTO memberSecurityDTO =
//					new MemberSecurityDTO(
//							member.getMid(),
//							member.getMpw(),
//							member.getEmail(),
//							member.isDel(),
//							member.isSocial(),
//							member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList())
//
//
//					);
//			return memberSecurityDTO;
//		}
//	}
//
//	private String getKakaoEmail(Map<String, Object> paramMap) {
//		log.info("kakao...............");
//
//		Object value = paramMap.get("kakao_account");
//
//		log.info(value);
//
//		LinkedHashMap accountMap = (LinkedHashMap) value;
//
//		String email = (String) accountMap.get("email");
//
//		log.info(email);
//
//		return email;
//	}
//
//}
