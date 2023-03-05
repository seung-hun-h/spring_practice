package hello.embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfig;

public class EmbedTomcatSpringMain {
	public static void main(String[] args) throws LifecycleException {
		System.out.println("EmbedTomcatSpringMain.main");

		// tomcat
		Connector connector = new Connector();
		connector.setPort(8080);

		Tomcat tomcat = new Tomcat();
		tomcat.setConnector(connector);

		// spring container
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(HelloConfig.class);

		// spring mvc dispatcher
		DispatcherServlet dispatcher = new DispatcherServlet(applicationContext);

		Context context = tomcat.addContext("", "/");
		tomcat.addServlet("", "dispatcher", dispatcher);
		context.addServletMappingDecoded("/", "dispatcher");

		tomcat.start();
	}
}
