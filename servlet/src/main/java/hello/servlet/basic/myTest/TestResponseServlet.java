package hello.servlet.basic.myTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testResponseServlet", urlPatterns = "/test-response")
public class TestResponseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); //2
        resp.setCharacterEncoding("utf-8"); //3

        String html = "<html>" +
                "<body>" +
                "<h2>This is Html Example</h2>" +
                "<h3>Hi I'm Seunghun</h3>" +
                "<h3>제 이름은 가나다입니다</h3>" +
                "</body>" +
                "</html>";

        resp.getWriter().write(html);

        Cookie cookie = new Cookie("Test-Cookie", "hello"); // 4
        cookie.setMaxAge(1200); // 5
        resp.addCookie(cookie); // 6

    }
}
