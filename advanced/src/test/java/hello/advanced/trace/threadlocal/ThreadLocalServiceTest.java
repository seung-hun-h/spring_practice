package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.TestUtil;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();
    
    @Test 
    public void field() throws Exception {
        log.info("main start");
        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("threadA");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start();
        TestUtil.sleep(100);
        threadB.start();
        TestUtil.sleep(2000); // main thread 종료까지 기다림
    }
    
    
}
