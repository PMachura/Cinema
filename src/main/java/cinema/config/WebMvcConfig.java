package cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private WebFlowConfig webFlowConfig;

    @Bean
    public FlowHandlerMapping flowHandlerMapping(){
    	FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
    	flowHandlerMapping.setFlowRegistry(webFlowConfig.flowRegistry());
    	return flowHandlerMapping;
    }
    
    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(){
    	FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter();
    	flowHandlerAdapter.setFlowExecutor(webFlowConfig.flowExecutor());
    	return flowHandlerAdapter;
    }
}
