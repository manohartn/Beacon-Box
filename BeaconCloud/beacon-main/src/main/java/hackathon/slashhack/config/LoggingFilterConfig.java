/**
 * Copyright (C) 1999 - 2015 Ticketmaster
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package hackathon.slashhack.config;

import com.google.common.collect.ImmutableSet;
import com.ticketmaster.monitoring.servletlogging.filter.LoggingFilter;
import com.ticketmaster.platform.service.foundation.catalog.model.GitInfo;
import com.ticketmaster.platform.service.foundation.logging.model.Enrichments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingFilterConfig {

    private final static int LOGGING_REQUEST_BODY_LENGTH = 50;
    private final static int LOGGING_RESPONSE_BODY_LENGTH = 50;

    @Autowired(required = false)
    private GitInfo gitInfo;

    @Autowired
    private String applicationVersion;

    @Autowired
    private LoggingFilter loggingFilter;

    /**
     * Configurate the servlet logging filter.
     *
     * @return
     */
    @Bean(name = "enrichments")
    public Enrichments loggingFilterConfig() {
        loggingFilter.setHeadersToPrint(ImmutableSet.<String>of(
                "TMPS-Hostname-Stack",
                "TMPS-Client-Hostname",
                "TMPS-Client-App-Version",
                "TMPS-Request-Id",
                "TMPS-Session-Id",
                "TMPS-Correlation-Id",
                "TMPS-Service-Version"
            )
        );

        loggingFilter.setIncludeRequestBody(false);
        loggingFilter.setIncludeRequestBodyOnError(false);
        loggingFilter.setIncludeResponseBody(false);
        loggingFilter.setIncludeResponseBodyOnError(false);
        loggingFilter.setRequestBodyLength(LOGGING_REQUEST_BODY_LENGTH);
        loggingFilter.setResponseBodyLength(LOGGING_RESPONSE_BODY_LENGTH);
        loggingFilter.setRethrowCaughtException(false);
        loggingFilter.setRethrowUncaughtException(false);
        loggingFilter.setIncludeElapsedTime(false);
        loggingFilter.setIncludeHttpStatus(true);
        loggingFilter.setIncludePathPattern(false);
        loggingFilter.setIncludeParam(true);
        loggingFilter.setMaskDigitSequence(false);

        return configAdditionalLoggingInfo();
    }

    /**
     * Adding additional key/value pairs onto the servlet logline.  If nothing is needed to add, just simply return
     * an empty Enrichments object.
     *
     * @return Enrichments object contains set of key/value pairs to be inserted into each servlet logline.  Make sure
     * these additional information is needed because it may costs our Splunk / Logging Service spaces.
     */
    private Enrichments configAdditionalLoggingInfo() {
        Enrichments enrichments = new Enrichments();

        if (gitInfo != null) {
            enrichments.add("gitCommitId", gitInfo.getCommitId());
            enrichments.add("gitRemoteOriginUrl", gitInfo.getRemoteOriginUrl());
        }

        enrichments.add("appVersion", applicationVersion);

        return enrichments;
    }
}