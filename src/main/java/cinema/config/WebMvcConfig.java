package cinema.config;


import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return container -> container.addErrorPages(new ErrorPage(MultipartException.class,"/movie/uploadError"));
    }
}
