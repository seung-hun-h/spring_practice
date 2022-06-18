package hello.jdbc.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DBConnectionUtilsTest {
    @Test
    @DisplayName("DB 커넥션 생성 성공")
    void connection() {
        // given
        Connection connection = DBConnectionUtils.getConnection();
        // when & then
        assertThat(connection).isNotNull();
    }
}