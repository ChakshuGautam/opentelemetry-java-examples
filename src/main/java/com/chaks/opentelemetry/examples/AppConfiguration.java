package com.chaks.opentelemetry.examples;

import com.lightstep.opentelemetry.launcher.OpenTelemetryConfiguration;
import org.springframework.beans.factory.annotation.Value;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration {

    @Value("${lightstep-token}")
    private String lightstepToken;

    @Value("${service-name}")
    private String serviceName;

    @Value("${lightstep-host}")
    private String lightstepHost;

    @Bean
    public Tracer getRestTemplate() {
        OpenTelemetryConfiguration.newBuilder()
                .setServiceName(serviceName)
                .setAccessToken(lightstepToken)
                .setTracesEndpoint(lightstepHost)
                .install();

        Tracer tracer = GlobalOpenTelemetry
                .getTracer("example-library", "1.0.0");
        return tracer;
    }

}
