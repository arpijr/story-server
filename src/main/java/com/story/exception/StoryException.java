package com.story.exception;

public class StoryException extends RuntimeException {
    public StoryException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public StoryException(String exMessage) {
        super(exMessage);
    }
}
