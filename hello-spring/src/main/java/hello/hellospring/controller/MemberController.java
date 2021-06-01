package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //어노테이션으로 인해서 스프링 자체적으로 컨트롤러 객체를 생성하고 가지고 있는다.
public class MemberController {

    /**
     *여러 컨트롤러에서 memberService를 사용한다. ex) 주문 컨트롤러 등.
     *따라서 새로운 인스턴스를 계속 생성할 필요없다.
     */
    private final MemberService memberService;

    @Autowired // memberService를 스프링 컨테이너의 memberService와 연결해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
