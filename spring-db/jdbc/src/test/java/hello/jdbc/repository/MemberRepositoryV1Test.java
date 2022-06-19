package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV1Test {

    private MemberRepositoryV1 repository;

    @BeforeEach
    void setUp() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(URL);
//        dataSource.setUsername(USERNAME);
//        dataSource.setPassword(PASSWORD);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV3", 1000);
        // create
        repository.save(member);

        // read
        Member findMember = repository.findMember(member.getMemberId());
        assertThat(member).isEqualTo(findMember);

        // update
        repository.updateMember(member.getMemberId(), 2000);
        Member updateMember = repository.findMember(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(2000);

        // delete
        repository.deleteMember(member.getMemberId());
        assertThatThrownBy(() -> repository.findMember(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}