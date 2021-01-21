package utilities.messages;

public class SendChat extends MessageBody {

    private final String message;
    private final int to;

    public SendChat(String message, int to) {

        this.message = message;
        this.to = to;

    }

    public String getMessage() {
        return message;
    }

    public int getTo() {
        return to;
    }
}
