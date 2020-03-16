package com.rbtsb.tngcrsapi.config;

import com.rbtsb.tngcrsapi.TngCrsApiApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = TngCrsApiApplication.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
public class ApplicationConfig {

    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";

//    @Autowired
//    JpaConfig jpaConfig;

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }
}
