package server.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.messages.MessageBody;

public class MessageHandler {

    Gson gson = new Gson();

    public String buildMessage(String messageType, MessageBody messageBody) {

        Message message = new Message(messageType, messageBody);

        return gson.toJson(message);

    }

    public Message handleMessage(String incomingMessage) {

        return gson.fromJson(incomingMessage, Message.class);

    }

}
