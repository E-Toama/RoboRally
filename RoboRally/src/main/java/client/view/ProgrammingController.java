package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProgrammingController {

    GridPane gridPane;
    int registerCounter = 0;

    public ProgrammingController(String[] cards) {
            //Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/ProgrammingMat.fxml"));
            gridPane = new GridPane();
        createCardButtons(cards);
    }



    public void createCardButtons(String[] cards) {

        for (int i = 0; i < 9; i++) {
            ProgrammingButton cardButton = new ProgrammingButton(i, cards[i]);
            Label label = new Label();
            label.setId(String.valueOf(i));
            cardButton.setOnAction(e -> selectCard(cardButton, label));
            gridPane.add(cardButton, 0, i);
            gridPane.add(label, 1, i);
        }
    }

    private void selectCard(ProgrammingButton button, Label label) {
        if (button.isChosen()) {
            button.setChosen(false);
            registerCounter--;
        } else {
            button.setChosen(true);
            //TODO: Highlight chosen card by switch to image with different color
            registerCounter++;
            label.setText(String.valueOf(registerCounter));
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    /*MoveI, MoveII, MoveIII, TurnLeft, TurnRight, UTurn, BackUp, PowerUp und Again.*/

}
