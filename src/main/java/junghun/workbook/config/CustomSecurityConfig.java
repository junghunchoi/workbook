package junghun.workbook.config;


import junghun.workbook.security.Custom403Handler;
import junghun.workbook.security.CustomUserDetailService;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 필요한 화면에만 보안설정을 할 수있는 어노테이션
public class CustomSecurityConfig {

	private final DataSource dataSource; // 쿠키와 관련된 정보를 테이블로 보관
	private final CustomUserDetailService userDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		log.info("---- security config ---");

		http.formLogin().loginPage("/member/login");

		http.csrf().disable();

		http.rememberMe().key("12345678").tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailService)
				.tokenValiditySeconds(60*60*24*30);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); // 403



		return http.build();
	}


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		/*
		정적 자원(css 등)을 호출할 때 필터가 동작하지 않는다.
		 */

		log.info("--- web config ---");

		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);

		return repo;
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new Custom403Handler();
	}

}
