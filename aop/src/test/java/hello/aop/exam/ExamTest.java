package hello.aop.exam;

import hello.aop.exam.aop.RetryAspect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TraceAspect.class, RetryAspect.class})
@Slf4j
@SpringBootTest
public class ExamTest {

  @Autowired
  private ExamService examService;

  @Test
  public void test() throws Exception {
    for (int i = 0; i < 5; i++) {
      examService.request("data" + 1);
    }
  }
}
