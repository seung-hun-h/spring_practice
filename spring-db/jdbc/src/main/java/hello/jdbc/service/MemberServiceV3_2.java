package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

@Slf4j
public class MemberServiceV3_2 {
    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        // 트랜잭션 시작
        txTemplate.executeWithoutResult((status) -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (Exception exception) {
                throw new IllegalStateException(exception);
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
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
