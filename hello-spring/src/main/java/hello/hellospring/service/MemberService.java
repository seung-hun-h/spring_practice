package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repogitory.MemberRepogitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepogitory memberRepogitory;

    public MemberService(MemberRepogitory memberRepogitory) {
        this.memberRepogitory = memberRepogitory;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepogitory.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepogitory.findByName(member.getName())
                .ifPresent(m -> { //Optional이기 때문에 가능한 방법
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepogitory.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepogitory.findById(memberId);
    }

}
