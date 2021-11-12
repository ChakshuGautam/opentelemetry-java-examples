package com.chaks.opentelemetry.examples;


import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(ExamplesApplication.class, args);
    }

    @GetMapping("/")
    ResponseEntity<String> test() {
        Span span = tracer.spanBuilder("start example").startSpan();
        span.end();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
