package com.chaks.opentelemetry.examples.controllers;

import com.chaks.opentelemetry.examples.services.MockException;
import com.chaks.opentelemetry.examples.services.MockNetworkService;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    Tracer tracer;

    @GetMapping("/simple/okay")
    ResponseEntity<String> okayMessage() {
        Span span = tracer.spanBuilder("Simple Controller").startSpan();

        int data;
        try (Scope scope = span.makeCurrent()) {
            Span childSpan = tracer.spanBuilder("child")
                    .setParent(Context.current().with(span))
                    .startSpan();
            data = new MockNetworkService(childSpan).mockCall();
        } finally {
            span.end();
        }
        return new ResponseEntity<>("OK - " + data, HttpStatus.OK);
    }

    @GetMapping("/simple/error")
    ResponseEntity<String> errorMessage() {
        Span span = tracer.spanBuilder("Simple Controller - Error").startSpan();
        int data = 0;
        try {
            Span childSpan = tracer.spanBuilder("child")
                    .setParent(Context.current().with(span))
                    .startSpan();
            data = new MockNetworkService(childSpan).mockCallWithError();
        } catch (MockException e) {
            return new ResponseEntity<>("Error - " + data, HttpStatus.EXPECTATION_FAILED);
        }
        span.end();
        return new ResponseEntity<>("OK - " + data, HttpStatus.OK);
    }
}
