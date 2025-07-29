package com.example.practice_spring_data_jpa;


import com.example.practice_spring_data_jpa.shared.config.AppConfig;
import com.example.practice_spring_data_jpa.shared.config.WebConfig;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class PracticeSpringDataJpaApplication {

	public static void main(String[] args) throws Exception {
		// Spring Context (Web + App)
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class, WebConfig.class);

		// DispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

		// Tomcat Embedded
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("temp");
		tomcat.setPort(8080);
		tomcat.getConnector();

		var ctx = tomcat.addContext("", null);

		Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet);
		ctx.addServletMappingDecoded("/", "dispatcher");

		tomcat.start();
		tomcat.getServer().await();
	}
}
