package server.messages;

public class ReceivedChat extends MessageBody {

    private final String message;
    private final String from;
    private final Boolean isPrivate;

    public ReceivedChat(String message, String from, Boolean isPrivate) {

        this.message = message;
        this.from = from;
        this.isPrivate = isPrivate;

    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }
}