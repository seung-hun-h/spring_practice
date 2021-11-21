package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();
    
    @Test 
    public void field() throws Exception {
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

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
