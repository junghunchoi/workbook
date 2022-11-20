package junghun.workbook.Repository;


import java.util.Optional;
import javax.persistence.Entity;
import junghun.workbook.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid  and m.social = false")
    Optional<Member> getWithRoles(@Param("mid") String mid);

}
