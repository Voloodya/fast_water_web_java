package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages ={ "config","controller","dao","entity","service","weatherAPI"})
public class WebConfig implements WebMvcConfigurer {

    //Регистрация контроллера представления, которые создают прямое сопоставление между URL-адресом
    // и именем представления
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("main_page");
    }

    //Указываем где будут лежать ресурсы нашего проекта, такие как css, image, js и другие
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    //Bean указывающии какие странцы откуда тянуть (куда смотреть).
    // Подтягивает страницы только по имени без пути
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/pages/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
