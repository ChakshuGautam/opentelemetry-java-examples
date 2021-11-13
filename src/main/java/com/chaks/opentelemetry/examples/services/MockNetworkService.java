package com.chaks.opentelemetry.examples.services;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;

public class MockNetworkService {

    private final Span childSpan;

    public MockNetworkService(Span childSpan) {
        this.childSpan = childSpan;
    }

    @WithSpan(value="mockCall")
    public int mockCall(){
        try {
            Thread.sleep(1000);
            childSpan.addEvent("Done");
            childSpan.end();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @WithSpan(value="mockException")
    public int mockCallWithError() throws MockException {
        throw new MockException("MockException");
    }
}

