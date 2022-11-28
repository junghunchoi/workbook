package junghun.workbook.config;


import javax.sql.DataSource;
import junghun.workbook.security.CustomUserDetailService;
import junghun.workbook.security.handler.Custom403Handler;
import junghun.workbook.security.handler.CustomSocialLoginSuccessHandler;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 필요한 화면에만 보안설정을 할 수있는 어노테이션
public class CustomSecurityConfig {

    //주입필요
    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;


    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    // 설정을 통해 사용자가 접근을 제어한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-------------configure-----------");

        //로그인 화면에서 로그인을 진행 및 아래의 템플릿을 사용
        http.formLogin().loginPage("/member/login");

        //csrf란 보안기술이 있는데 이를 처리하기 위한 과정이 복잡하므로 disable처리한다.
        http.csrf().disable();

        http.rememberMe().key("12345678").tokenRepository(persistentTokenRepository())
            .userDetailsService(userDetailService)
            .tokenValiditySeconds(60 * 60 * 24 * 30); //쿠키의 유효기간 -> 30일

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        //oauth2 로그인을 사용하다는 설정
        http.oauth2Login().loginPage("/member/logon")
            .successHandler(authenticationSuccessHandler());

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }


    // js ,css 같이 정적인 파일에는 필터링 하지않도록 조치
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("-----web configure-------");

        return (web -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //remeber-me 쿠키를 관리하기 위한 메소드
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }
}
