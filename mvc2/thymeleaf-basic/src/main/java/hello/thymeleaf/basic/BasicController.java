package hello.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

  @GetMapping("text-basic")
  public String textBasic(Model model) {
    model.addAttribute("data", "Hello Spring!");

    return "basic/text-basic";
  }

  @GetMapping("text-unescaped")
  public String textUnescaped(Model model) {
    model.addAttribute("data", "Hello Spring!");

    return "basic/text-unescaped";
  }

  @GetMapping("/variable")
  public String textVariable(Model model) {

    User userA = new User("userA", 10);
    User userB = new User("userB", 20);

    List<User> users = List.of(userA, userB);
    Map<String, User> userMap = Map.of("userA", userA, "userB", userB);

    model.addAttribute("user", userA);
    model.addAttribute("users", users);
    model.addAttribute("userMap", userMap);

    return "basic/variable";
  }

  @GetMapping("/basic-objects")
  public String basicObjects(HttpSession httpSession) {
    httpSession.setAttribute("sessionData", "Hello Session");
    return "basic/basic-objects";
  }

  @GetMapping("/date")
  public String date(Model model) {
    model.addAttribute("localDateTime", LocalDateTime.now());
    return "basic/date";
  }

  @GetMapping("/link")
  public String link(Model model) {
    model.addAttribute("param1", "data1");
    model.addAttribute("param2", "data2");

    return "basic/link";
  }

  @GetMapping("/literal")
  public String literal(Model model) {
    model.addAttribute("data", "Spring!");
    return "basic/literal";
  }

  @GetMapping("/operation")
  public String operation(Model model) {
    model.addAttribute("nullData", null);
    model.addAttribute("data", "Spring!");
    return "basic/operation";
  }

  @GetMapping("/attribute")
  public String attribute() {
    return "basic/attribute";
  }

  @Component("helloBean")
  static class HelloBean {
    public String hello(String data) {
      return "Hello " + data;
    }
  }

  @GetMapping("/each")
  public String each(Model model) {
    addUsers(model);

    return "basic/each";
  }

  @GetMapping("/condition")
  public String condition(Model model) {
    addUsers(model);
    return "basic/condition";
  }

  @GetMapping("/comments")
  public String comments(Model model) {
    model.addAttribute("data", "Spring!");

    return "basic/comments";
  }

  @GetMapping("/block")
  public String block(Model model) {
    model.addAttribute("data", "Spring!");

    return "basic/block";
  }

  @GetMapping("/javascript")
  public String javascript(Model model) {
    model.addAttribute("data", "Spring!");

    return "basic/javascript";
  }

  private void addUsers(Model model) {
    List<User> users = new ArrayList<>();

    users.add(new User("userA", 10));
    users.add(new User("userB", 20));
    users.add(new User("userC", 30));

    model.addAttribute("users", users);
  }

  @Getter
  static class User {
    private String username;
    private int age;

    @Builder
    public User(String username, int age) {
      this.username = username;
      this.age = age;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("User{");
      sb.append("username='").append(username).append('\'');
      sb.append(", age=").append(age);
      sb.append('}');
      return sb.toString();
    }
  }
}

