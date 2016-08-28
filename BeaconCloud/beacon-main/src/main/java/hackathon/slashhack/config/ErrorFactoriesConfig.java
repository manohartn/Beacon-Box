/**
 * Copyright (C) 1999 - 2014 Ticketmaster
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package hackathon.slashhack.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.ticketmaster.platform.service.foundation.errors.DefaultErrorDataFactory;
import com.ticketmaster.platform.service.foundation.errors.ErrorData;
import com.ticketmaster.platform.service.foundation.errors.ErrorDataFactory;
import com.ticketmaster.platform.service.foundation.errors.SimpleErrorDataFactory;
import com.ticketmaster.platform.service.foundation.util.ExampleCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author dannwebster
 */
@ExampleCode("example of how to set up basic error handling")
@Configuration
public class ErrorFactoriesConfig {

    @Bean
    public ErrorDataFactory getErrorDataFactory() {
        return new SimpleErrorDataFactory(
            ImmutableSet.<Class<? extends Exception>>of(IllegalArgumentException.class),
            new ErrorData(
                "illegal.argument",
                "validation.error",
                HttpStatus.BAD_REQUEST.value(),
                "Validation Violation",
                ErrorData.ErrorType.USER,
                true,
                false
            )
        );
    }

    @Bean
    public List<ErrorDataFactory> errorDataFactoryList() {
        List<ErrorDataFactory> list = ImmutableList.of(getErrorDataFactory(),
            DefaultErrorDataFactory.INSTANCE);

        return list;
    }

}
