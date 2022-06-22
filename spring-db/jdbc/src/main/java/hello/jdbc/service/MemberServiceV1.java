package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;

import java.sql.SQLException;

public class MemberServiceV1 {
    private final MemberRepositoryV1 memberRepository;

    public MemberServiceV1(MemberRepositoryV1 memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findMember(fromId);
        Member toMember = memberRepository.findMember(toId);

        memberRepository.updateMember(fromMember.getMemberId(), fromMember.getMoney() - money);
        validate(toMember);
        memberRepository.updateMember(toMember.getMemberId(), toMember.getMoney() + money);

    }

    private void validate(Member toMember) {
        if ("ex".equals(toMember.getMemberId())) {
            throw new IllegalStateException("예외가 발생했습니다.");
        }
    }
}
