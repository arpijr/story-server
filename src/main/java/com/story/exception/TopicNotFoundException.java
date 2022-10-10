package com.story.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String message) {
        super(message);
    }
}
