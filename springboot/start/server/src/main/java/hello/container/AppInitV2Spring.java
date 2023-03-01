package hello.container;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;

public class AppInitV2Spring implements AppInit {
	@Override
	public void onStartup(ServletContext servletContext) {
		System.out.println("AppInitV2Spring.onStartup");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(HelloConfig.class);

		DispatcherServlet dispatcher = new DispatcherServlet(applicationContext);

		servletContext.addServlet("dispatcherV2", dispatcher)
			.addMapping("/spring/*");
	}
}
