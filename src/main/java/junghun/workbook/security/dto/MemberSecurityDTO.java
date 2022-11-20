package junghun.workbook.security.dto;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MemberSecurityDTO {

    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;

    public MemberSecurityDTO(String username, String password, String email, boolean del, boolean social
        , Collection<? extends GrantedAuthority> authorities) {

        super(username,password,authorities);

        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.del = del;
        this.social = social;
    }

}
