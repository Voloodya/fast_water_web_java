package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//Класс инициализатор для загрузки приложения, который загружает конфигурацию WEB and Spring
//WebApplicationInitializer реализации обнаруживаются автоматически, поэтому можно свободно упаковывать их в свое приложение по своему усмотрению
public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        context.register(HibernateConfig.class, WebConfig.class,SpringConfig.class);
        context.setServletContext(servletContext);

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dispather =
                servletContext.addServlet("dispather", servlet);
        dispather.setLoadOnStartup(1);
        dispather.addMapping("/");
    }
}
