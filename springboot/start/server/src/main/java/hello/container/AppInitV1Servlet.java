package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;

public class AppInitV1Servlet implements AppInit {
	@Override
	public void onStartup(ServletContext servletContext) {
		servletContext.addServlet("helloServlet", new HelloServlet())
			.addMapping("/hello-servlet");
	}
}
