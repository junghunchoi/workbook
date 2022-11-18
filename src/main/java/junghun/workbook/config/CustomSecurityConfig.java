package junghun.workbook.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 필요한 화면에만 보안설정을 할 수있는 어노테이션
public class CustomSecurityConfig {


	// 설정을 통해 사용자가 접근을 제어한다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		log.info("-------------configure-----------");

		//로그인 화면에서 로그인을 진행 및 아래의 템플릿을 사용
		http.formLogin().loginPage("/member/login");

		//csrf란 보안기술이 있는데 이를 처리하기 위한 과정이 복잡하므로 disable처리한다.
		http.csrf().disable();

		return http.build();
	}

	// js ,css 같이 정적인 파일에는 필터링 하지않도록 조치
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("-----web configure-------");

		return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



}
