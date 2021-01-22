package client.view;

import com.sun.javafx.scene.control.SelectedCellsMap;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import utilities.messages.SelectCard;

import java.io.IOException;
import java.util.HashMap;

public class ProgrammingController {

    GridPane gridPane;
    int registerCounter = 0;
    Label[] labelList = new Label[9];
    ProgrammingButton[] buttonList = new ProgrammingButton[9];
    HashMap<Label, Button> labelButtonMap = new HashMap<>();
    Button sendButton;
    Label timerLabel;

    public ProgrammingController(String[] cards) {
        gridPane = new GridPane();
        createCardButtons(cards);
        Button sendButton = new Button("SEND");
        sendButton.setDisable(true);
        sendButton.setOnAction(e -> confirmChoice());
        this.sendButton = sendButton;
        timerLabel = new Label("Timer here!");
        timerLabel.setVisible(false);
        gridPane.addColumn(9, sendButton, timerLabel);
    }

    private void confirmChoice() {
        timerLabel.setVisible(true);
        for (ProgrammingButton button : buttonList) {
            button.setDisable(true);
        }
    }

    private void allRegistersChosen() {
        if (registerCounter == 5) {
            sendButton.setDisable(false);
            sendButton.setStyle("-fx-background-color: GREEN");
            for (ProgrammingButton button : buttonList) {
                    if (!button.isChosen()) {
                        button.setDisable(true);
                    }
                }
            }
    }

    private void lastRegisterFree() {
        if (registerCounter == 4) {
            sendButton.setDisable(true);
            sendButton.setStyle("-fx-background-color: WHITE");
            for (ProgrammingButton button : buttonList) {
                    button.setDisable(false);
            }
        }
    }



    public void createCardButtons(String[] cards) {

        for (int i = 0; i < 9; i++) {
            ProgrammingButton cardButton = new ProgrammingButton(i, cards[i]);
            Label label = new Label();
            label.setId(String.valueOf(i));
            label.setPrefWidth(91);
            label.setAlignment(Pos.CENTER);
            labelButtonMap.put(label, cardButton);
            labelList[i] = label;
            cardButton.setOnAction(e -> selectCard(cardButton, label));
            buttonList[i] = cardButton;
            gridPane.add(cardButton, i, 0);
            gridPane.add(label, i, 1);
        }
    }

    private void selectCard(ProgrammingButton button, Label label) {
        if (button.isChosen()) {
            button.setChosen(false);
            button.setStyle("-fx-background-color: #CED0CE");
            registerCounter--;
            updateLabels(label);
            label.setText("");
            lastRegisterFree();
        } else {
            button.setChosen(true);
            button.setStyle("-fx-background-color: PURPLE");
            registerCounter++;
            label.setText("Register: " + String.valueOf(registerCounter));
            allRegistersChosen();
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void updateLabels(Label currentLabel) {
        for (Label label : labelList) {
            if (!label.getText().isEmpty()) {
                int registerNumber = Integer.parseInt(label.getText().split(" ")[1]);
                if (registerNumber > Integer.parseInt(currentLabel.getText().split(" ")[1])) {
                    registerNumber--;
                    label.setText("Register: " + registerNumber);
                }
            }

        }
    }

}
