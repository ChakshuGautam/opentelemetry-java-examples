package com.chaks.opentelemetry.examples.services;

public class MockException extends Exception {
    String message;

    MockException(String message) {
        this.message = message;
    }
}
