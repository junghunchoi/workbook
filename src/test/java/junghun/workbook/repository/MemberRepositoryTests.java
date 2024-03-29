package junghun.workbook.repository;


import java.util.Optional;
import java.util.stream.IntStream;
import junghun.workbook.Repository.MemberRepository;
import junghun.workbook.entity.Member;
import junghun.workbook.entity.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void insertMember() {
        IntStream.rangeClosed(1,100).forEach(i->{

            Member member = Member.builder()
                .mid("member"+i)
                .mpw(passwordEncoder.encode("1111"))
                .email("email"+i+"@aaa.bbb")
                .build();

            member.addRole(MemberRole.USER);


            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.getWithRoles("member99");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }

    @Commit
    @Test
    public void testUpdate() {

        String mid = "azzudi@naver.com";
        String mpw = passwordEncoder.encode("54321");

        memberRepository.updatePassword(mpw,mid);
    }
}
