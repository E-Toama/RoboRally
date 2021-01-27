package client.view;

import client.viewmodel.ProgrammingViewModel;
import com.sun.javafx.event.CompositeEventHandler;
import com.sun.javafx.scene.control.SelectedCellsMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.messages.SelectCard;
import java.io.IOException;
import java.util.HashMap;

public class ProgrammingController {

    ProgrammingViewModel programmingViewModel;

    GridPane gridPane;
    int registerCounter = 1;
    Label[] labelList = new Label[9];
    ProgrammingButton[] buttonList = new ProgrammingButton[9];
    HashMap<Label, ProgrammingButton> labelButtonMap = new HashMap<>();
    Button sendButton;
    Label timerLabel;
    private final Integer startTime = 30;
    private Integer seconds = startTime;


    public void initialize() {
        timerLabel.textProperty().bindBidirectional(programmingViewModel.getTimerLabelProperty());

    }

    public ProgrammingController(ProgrammingViewModel programmingViewModel) {
        this.programmingViewModel = programmingViewModel;
        gridPane = new GridPane();
        gridPane.setMinHeight(190);
        gridPane.setMaxHeight(190);
        gridPane.setMinWidth(975);
        gridPane.setMaxWidth(975);
        createCardButtons(programmingViewModel.getCards());
        Button sendButton = new Button("SEND");
        sendButton.setDisable(true);
        sendButton.setOnAction(e -> confirmChoice());
        this.sendButton = sendButton;
        timerLabel = new Label("LEER");
        timerLabel.setVisible(true);
        gridPane.addColumn(9, timerLabel);
        //initialize();
        //programmingViewModel.setTimer();
        initiateTimer();

    }

    private void confirmChoice() {
        timerLabel.setVisible(true);
        for (ProgrammingButton button : buttonList) {
            button.setDisable(true);
        }
        programmingViewModel.selectionFinished();
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
            programmingViewModel.selectCard("empty", registerCounter);
        } else {
            label.setText("Register: " + String.valueOf(registerCounter));
            programmingViewModel.selectCard(button.getCardString(), registerCounter);
        }
    }

    public void setRegisterActive(int register) {

        for (HashMap.Entry<Label,ProgrammingButton> entry : labelButtonMap.entrySet())
        {
            Label label = entry.getKey();
            if (label.getText().isEmpty()){
                continue;
            }
            int registerNumber = Integer.parseInt(label.getText().split(" ")[1]);
            if (register == registerNumber) {
                ProgrammingButton button =  entry.getValue();

                if (button.isChosen()) {
                    registerCounter--;
                    button.setChosen(false);
                    button.setStyle("-fx-background-color: #CED0CE");
                    label.setText("");
                    updateLabels(label);
                    lastRegisterFree();

                } else {
                    registerCounter++;
                    button.setChosen(true);
                    button.setStyle("-fx-background-color: PURPLE");
                    label.setText("Register: " + String.valueOf(register));
                    allRegistersChosen();
                }

            }
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

    /**
     * Starts timer of 30 seconds in programmingView
     *
     */
    private void initiateTimer(){

        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if(time!= null){
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

                seconds--;
                timerLabel.setText("Timer is running: " +seconds.toString());
                timerLabel.setTextFill(Color.RED);

                if(seconds <= 0){
                    time.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Timer run out!");
                    alert.show();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();

    }


}
