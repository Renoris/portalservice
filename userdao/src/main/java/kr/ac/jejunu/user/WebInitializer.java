package kr.ac.jejunu.user;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext=new AnnotationConfigWebApplicationContext();
        applicationContext.setNamespace("dispatcher-servlet");
        ServletRegistration.Dynamic servletRegistration= servletContext.addServlet("dispatcher",new DispatcherServlet(applicationContext));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
