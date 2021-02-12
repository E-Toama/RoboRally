package utilities;

import com.google.gson.*;
import game.gameboard.GameBoardMapObject;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import utilities.messages.GameStarted;
import utilities.messages.Message;
import utilities.messages.MessageBody;

import java.lang.reflect.Type;

/**
 * Doc: Dennis
 */
public class MessageHandler {

    public String buildMessage(String messageType, MessageBody messageBody) {

        Gson gson = new Gson();

        Message message = new Message(messageType, messageBody);

        return gson.toJson(message);

    }

    public Message handleMessage(String incomingMessage) {

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

            Gson gsonGameBoardBuilder = new GsonBuilder().registerTypeAdapter(GameBoardFieldObject.class, new GameBoardDeserializer()).create();

            if(messageType != null) {

                if (messageType.equals("GameStarted")) {

                    JsonArray mapArray = body.getAsJsonArray("map");

                    GameBoardMapObject[] map = gsonGameBoardBuilder.fromJson(mapArray, GameBoardMapObject[].class);

                    GameStarted gameStarted = new GameStarted(map);

                    return new Message("GameStarted", gameStarted);

                } else try {

                    MessageBody messageBody = gson.fromJson(body, (Type) Class.forName("utilities.messages." + messageType));

                    return new Message(messageType, messageBody);

                } catch (ClassNotFoundException e) {

                    e.printStackTrace();

                }

            }

            return null;

        }

    }

    static class GameBoardDeserializer implements JsonDeserializer<GameBoardFieldObject> {

        @Override
        public GameBoardFieldObject deserialize(JsonElement jsonElement, Type typeofT, JsonDeserializationContext jsonDeserializationContext) throws JsonSyntaxException {

            JsonObject gameBoardMapObject = jsonElement.getAsJsonObject();
            String type = gameBoardMapObject.get("type").getAsString();

            Gson gson = new GsonBuilder().create();

            try {

                switch (type) {

                    case "Antenna":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.AntennaFieldObject"));

                    case "Belt":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.BeltFieldObject"));

                    case "ControlPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.ControlPointFieldObject"));

                    case "Empty":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.EmptyFieldObject"));

                    case "EnergySpace":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.EnergySpaceFieldObject"));

                    case "Gear":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.GearFieldObject"));

                    case "Laser":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.LaserFieldObject"));

                    case "Pit":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.PitFieldObject"));

                    case "PushPanel":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.PushPanelFieldObject"));

                    case "RestartPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.RestartPointFieldObject"));

                    case "RotatingBelt":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject"));

                    case "StartPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.StartPointFieldObject"));

                    case "Wall":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboard.gameboardfieldobjects.WallFieldObject"));

                }

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }


            return null;

        }

    }

}
