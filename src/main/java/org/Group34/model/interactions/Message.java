package org.Group34.model.interactions;

public class Message {
    private String message;
    private boolean received;
    private boolean inNew;

    public Message(String message, boolean received, boolean inNew) {
        this.message = message;
        this.received = received;
        this.inNew = inNew;
    }

    public String getMessage() {
        return message;
    }

    public boolean isReceived() {
        return received;
    }

    public boolean isInNew() {
        return inNew;
    }

    public void setInNew(boolean inNew) {
        this.inNew = inNew;
    }
}
