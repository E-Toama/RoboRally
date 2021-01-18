package utilities;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import game.gameboard.MapElement;
import game.gameboard.boardelements.Antenna;
import game.gameboardV2.GameBoardMapObject;
import game.gameboardV2.gameboardfieldobjects.AntennaFieldObject;
import game.gameboardV2.gameboardfieldobjects.BeltFieldObject;
import game.gameboardV2.gameboardfieldobjects.GameBoardFieldObject;
import utilities.messages.GameStarted;
import utilities.messages.Message;
import utilities.messages.MessageBody;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

            Gson gsonGameBoardBuilder = new GsonBuilder().registerTypeAdapter(GameBoardFieldObject.class, new GameBoardDeserializer()).create();

            if(messageType != null) {

                if (messageType.equals("GameStarted")) {

                    JsonArray mapArray = body.getAsJsonArray("map");
                    Type mapType = new TypeToken<ArrayList<GameBoardMapObject>>() {}.getType();

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
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.AntennaFieldObject"));

                    case "Belt":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.BeltFieldObject"));

                    case "ControlPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.ControlPointFieldObject"));

                    case "Empty":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.EmptyFieldObject"));

                    case "EnergySpace":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.EnergySpaceFieldObject"));

                    case "Gear":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.GearFieldObject"));

                    case "Laser":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.LaserFieldObject"));

                    case "Pit":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.PitFieldObject"));

                    case "PushPanel":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.PushPanelFieldObject"));

                    case "RestartPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.RestartPointFieldObject"));

                    case "RotatingBelt":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.RotatingBeltFieldObject"));

                    case "StartPoint":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.StartPointFieldObject"));

                    case "Wall":
                        return gson.fromJson(jsonElement, (Type) Class.forName("game.gameboardV2.gameboardfieldobjects.WallFieldObject"));

                }

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }


            return null;

        }

    }

}
