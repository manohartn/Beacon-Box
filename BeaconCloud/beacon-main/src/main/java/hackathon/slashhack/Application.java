/**
 * Copyright (C) 1999 - 2015 Ticketmaster
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package hackathon.slashhack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ticketmaster.platform.service.foundation.config.EnableServiceFoundation;

@Configuration
@EnableAutoConfiguration(exclude = MetricRepositoryAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableServiceFoundation
@ComponentScan
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            public void contextInitialized(ServletContextEvent sce) {
                LOG.info("ServletContext initialized");
            }

            public void contextDestroyed(ServletContextEvent sce) {
                LOG.info("ServletContext destroyed");
            }
        };
    }

    @Bean(name = "applicationVersion")
    public String applicationVersion() {
        String version = getClass().getPackage().getImplementationVersion();

        return version == null ? "unknown" : version;
    }
}