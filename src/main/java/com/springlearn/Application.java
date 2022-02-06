package com.springlearn;

import com.springlearn.config.ApplicationConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Application  implements WebApplicationInitializer {

    @Override
    public void onStartup(@NotNull ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext annotationContext = new AnnotationConfigWebApplicationContext();
        annotationContext.register(ApplicationConfig.class);
        servletContext.addListener(new ContextLoaderListener(annotationContext));

        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet("dispatcher", new DispatcherServlet(annotationContext));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
