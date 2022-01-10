package hello.login.web.session;

import static org.assertj.core.api.Assertions.assertThat;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    public void sessionTest() throws Exception {


        //given
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSession(member, response);
        //when
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //then
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}