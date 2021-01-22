package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProgrammingController {

    GridPane gridPane;

    {
        try {
            gridPane = FXMLLoader.load(getClass().getResource("../../FXMLFiles/ProgrammingMat.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createCardButtons(String[] cards) {

        for (int i = 0; i < 9; i++) {
            Button cardButton = new Button();
            cardButton.setGraphic(createCardImage(cards[i]));
            cardButton.setId(String.valueOf(i));
        }
    }

    private static ImageView createCardImage(String cardType) {
        Image cardImage;
        switch (cardType) {
            case "MoveI":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "MoveII":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "MoveIII":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "TurnLeft":
                cardImage = new Image("a");
                return adjustToSlot(cardImage);
            case "TurnRight":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "UTurn":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "BackUp":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "PowerUp":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            case "Again":
                cardImage = new Image("");
                return adjustToSlot(cardImage);
            default:
                cardImage = new Image("");
                return adjustToSlot(cardImage);
        }
    }

    private static ImageView adjustToSlot(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(91);
        imageView.setFitHeight(154);
        return imageView;

    }

    /*MoveI, MoveII, MoveIII, TurnLeft, TurnRight, UTurn, BackUp, PowerUp und Again.*/

}
