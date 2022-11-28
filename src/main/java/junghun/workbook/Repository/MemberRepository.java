package junghun.workbook.Repository;


import java.util.Optional;
import javax.persistence.Entity;
import javax.transaction.Transactional;
import junghun.workbook.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid  and m.social = false")
    Optional<Member> getWithRoles(@Param("mid") String mid);


    /*
    @EntityGraph -> 종속된 엔티티는 쿼리실행시 select 하지않고 proxy 객체를 만들어 두고 프록시 객체를 호출할 때 마다
                    그때그떄 select 쿼리를 실행하여 지연로딩을 한다.
     */
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);


    @Modifying // update문을 사용하기 위한 어노테이션
    @Transactional
    @Query("update Member m set m.mpw = :mpw where m.mid = :mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);

}
