package junghun.workbook.security.handler;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {


    //403에러를 뱉을 때 ACCESS_DENIED 파라미터를 반환한다.
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.info("ACESS DENIED");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        // JSON 요청이였는지 확인
        String contentType = request.getHeader("Content-Type");

        boolean jsonRequest = contentType.startsWith("application/json");

        log.info("isJSON"+jsonRequest);

        //일반 request
        if (!jsonRequest) {
            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }
    }
}
