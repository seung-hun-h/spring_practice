package hello.embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import hello.servlet.HelloServlet;

public class EmbedTomcatServletMain {
	public static void main(String[] args) throws LifecycleException {
		System.out.println("EmbedTomcatServletMain.main");

		// tomcat
		Connector connector = new Connector();
		connector.setPort(8080);

		Tomcat tomcat = new Tomcat();
		tomcat.setConnector(connector);

		// servlet
		tomcat.addServlet("", "helloServlet", new HelloServlet());

		Context context = tomcat.addContext("", "/");
		context.addServletMappingDecoded("/hello-servlet", "helloServlet");

		tomcat.start();
	}
}
