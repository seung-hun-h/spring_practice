package hello.hellospring.repogitory;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJapMemberRepogitory extends JpaRepository<Member, Long>, MemberRepogitory {

//    JSQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
