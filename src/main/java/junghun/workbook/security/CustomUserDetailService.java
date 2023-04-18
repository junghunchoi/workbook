package junghun.workbook.security;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {

	private PasswordEncoder passwordEncoder;

	public CustomUserDetailService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}



	//UserDetails 는 사용자 인증 관련된 정보를 저장하는 인터페이스.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("loadUserByUsername: " + username);

		UserDetails userDetails = User.builder()
									  .username("user1")
									  .password(passwordEncoder.encode("1111"))
									  .authorities("ROLE_USER")
									  .build();

		return userDetails;
	}
		}
