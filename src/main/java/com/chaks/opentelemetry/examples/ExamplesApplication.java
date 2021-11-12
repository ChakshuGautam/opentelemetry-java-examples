package com.chaks.opentelemetry.examples;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lightstep.opentelemetry.launcher.OpenTelemetryConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ExamplesApplication {

    @Value("${lightstep-token}")
    private String lightstepToken;

    @Value("${service-name}")
    private String serviceName;

    @Value("${lightstep-host}")
    private String lightstepHost;

    public static void main(String[] args) {
        SpringApplication.run(ExamplesApplication.class, args);
    }

    @GetMapping("/")
    ResponseEntity<String> test() {
        System.out.println(serviceName + "  " + lightstepHost + "  " + lightstepHost);

        OpenTelemetryConfiguration.newBuilder()
                .setServiceName(serviceName)
                .setAccessToken(lightstepToken)
                .setTracesEndpoint(lightstepHost)
                .install();

        Tracer tracer = GlobalOpenTelemetry
                .getTracer("instrumentation-library-name", "1.0.0");
        Span span = tracer.spanBuilder("start example").startSpan();
        span.end();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
