package junghun.workbook.Repository;

import junghun.workbook.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

	@EntityGraph
	@Query("select m from Member m where m.mid = :mid and m.social = false ")
	Optional<Member> getWithRoles(String mid);
}
