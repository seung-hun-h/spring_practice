package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CallServiceV3 {

  private final InternalService internalService;

  public void external() {
    log.info("call external");
    internalService.internal(); // == [this.]internal()
  }
}
