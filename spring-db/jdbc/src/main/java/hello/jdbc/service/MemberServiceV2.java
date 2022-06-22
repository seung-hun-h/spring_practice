package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MemberServiceV2 {
    private final MemberRepositoryV2 memberRepository;
    private final DataSource dataSource;

    public MemberServiceV2(MemberRepositoryV2 memberRepository, DataSource dataSource) {
        this.memberRepository = memberRepository;
        this.dataSource = dataSource;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            connection.setAutoCommit(false);

            bizLogic(fromId, toId, money, connection);

            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
            throw new IllegalStateException(exception);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (Exception exception) {
                    log.info("error", exception);
                }
            }
        }



    }

    private void bizLogic(String fromId, String toId, int money, Connection connection) throws SQLException {
        Member fromMember = memberRepository.findMember(connection, fromId);
        Member toMember = memberRepository.findMember(connection, toId);

        memberRepository.updateMember(connection, fromMember.getMemberId(), fromMember.getMoney() - money);
        validate(toMember);
        memberRepository.updateMember(connection, toMember.getMemberId(), toMember.getMoney() + money);
    }

    private void validate(Member toMember) {
        if ("ex".equals(toMember.getMemberId())) {
            throw new IllegalStateException("예외가 발생했습니다.");
        }
    }
}
