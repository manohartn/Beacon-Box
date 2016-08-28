package hackathon.slashhack.config;

import com.ticketmaster.platform.service.foundation.health.HealthChecker;
import com.ticketmaster.platform.service.foundation.health.checker.DefaultHealthChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andy.chan on 1/23/15.
 */
@Configuration
public class HealthChecksConfig {

    @Bean
    public HealthChecker defaultHealthCheck() {
        return new DefaultHealthChecker();
    }
}
