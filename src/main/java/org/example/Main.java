package org.example;

import org.example.config.AppConfig;
import org.example.config.WebConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.catalina.startup.Tomcat;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class, WebConfig.class);

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