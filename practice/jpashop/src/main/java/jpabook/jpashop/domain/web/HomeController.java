package jpabook.jpashop.domain.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("Home controller");
        return "home";
    }
}
