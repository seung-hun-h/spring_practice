package hello.container;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class AppInitV3SpringMvc implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("AppInitV3SpringMvc.onStartup");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(HelloConfig.class);

		DispatcherServlet dispatcher = new DispatcherServlet(applicationContext);

		servletContext.addServlet("dispatcherV3", dispatcher)
			.addMapping("/");
	}
}
