package utilities.messages;

public class Message {

    private final String messageType;
    private final MessageBody messageBody;

    public Message(String messageType, MessageBody messageBody) {

        this.messageType = messageType;
        this.messageBody = messageBody;

    }

    public String getMessageType() {
        return messageType;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

}
