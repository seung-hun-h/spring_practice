package jpabook.jpashop.domain.web;

import jpabook.jpashop.domain.user.Address;
import jpabook.jpashop.domain.user.Role;
import jpabook.jpashop.domain.user.User;
import jpabook.jpashop.domain.web.dto.UserForm;
import jpabook.jpashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldErrors());
            return "users/createUserForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        User user = User.builder()
                .email(form.getEmail())
                .role(Role.MEMBER)
                .name(form.getName())
                .address(address)
                .picture(form.getPicture())
                .build();

        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "users/userList";
    }
}
