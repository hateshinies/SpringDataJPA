package base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean(name = "jspViewResolver")
    public ViewResolver getJspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("webapp/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    <servlet-mapping>
//    <servlet>dispatcher</servlet>
//    <url-pattern>/*</url-pattern>
//</servlet-mapping>
}
