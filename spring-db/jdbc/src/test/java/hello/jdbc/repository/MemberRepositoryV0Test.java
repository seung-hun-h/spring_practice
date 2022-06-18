package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    private MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV3", 1000);
        // create
        memberRepositoryV0.save(member);

        // read
        Member findMember = memberRepositoryV0.findMember(member.getMemberId());
        assertThat(member).isEqualTo(findMember);

        // update
        memberRepositoryV0.updateMember(member.getMemberId(), 2000);
        Member updateMember = memberRepositoryV0.findMember(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(2000);

        // delete
        memberRepositoryV0.deleteMember(member.getMemberId());
        assertThatThrownBy(() -> memberRepositoryV0.findMember(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}