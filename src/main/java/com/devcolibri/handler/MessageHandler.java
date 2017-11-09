package com.devcolibri.handler;

import java.util.concurrent.ConcurrentLinkedDeque;

public class MessageHandler {
    private ConcurrentLinkedDeque<String> concurrentLinkedDeque;

    public MessageHandler() {
        concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
    }

    public ConcurrentLinkedDeque<String> getQueue() {
        return concurrentLinkedDeque;
    }
}
