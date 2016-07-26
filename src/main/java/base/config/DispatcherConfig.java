package base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("base")

public class DispatcherConfig extends WebMvcConfigurerAdapter {

    // To use jsp
    @Bean
    ViewResolver internalViewResolver() {
        // the view resolver bean ...
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // config static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Don't forget the ending "/" for location or you will hit 404.
        registry.addResourceHandler("/img/**").addResourceLocations("/static/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
    }
}