package server.messages;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MessageHandler {

    public String buildMessage(String messageType, MessageBody messageBody) {

        Gson gson = new Gson();

        Message message = new Message(messageType, messageBody);

        return gson.toJson(message);

    }

    public Message handleMessage(String incomingMessage) {

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageDeserializer()).create();

        Message returnValue = null;

        try {

            returnValue = gson.fromJson(incomingMessage, Message.class);

        } catch (com.google.gson.JsonSyntaxException jsonSyntaxException) {

            jsonSyntaxException.printStackTrace();

        }

        return returnValue;

    }

    static class MessageDeserializer implements JsonDeserializer<Message> {

        @Override
        public Message deserialize(JsonElement jsonElement, Type typeofT, JsonDeserializationContext jsonDeserializationContext) throws JsonSyntaxException {

            Gson gson = new Gson();
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String messageType = jsonObject.get("messageType").getAsString();
            JsonObject body = jsonObject.get("messageBody").getAsJsonObject();

            if(messageType != null) {

                if (messageType.equals("GameStarted")) {

                    JsonArray mapArray = body.getAsJsonArray("map");
                    //Type mapType = new TypeToken<>()

                    GameStarted gameStarted = new GameStarted();

                    return new Message("GameStarted", gameStarted);

                } else try {

                    MessageBody messageBody = gson.fromJson(body, (Type) Class.forName("server.messages." + messageType));

                    return new Message(messageType, messageBody);

                } catch (ClassNotFoundException e) {

                    e.printStackTrace();

                }

            }

            return null;

        }

    }

    //static class GameBoardDeserializer implements JsonDeserializer<> {}

}
