package client.view;


import client.utilities.ImageBuilder;
import client.viewmodel.PlayerMatModel;
import game.Robots.Robot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PlayerMatController {

    private PlayerMatModel playerMatModel = new PlayerMatModel(this);

    @FXML
    private GridPane playerMatPane;

    @FXML
    private GridPane playerStatusPane;


    // Static text labels
    @FXML
    private Label checkPointLabel;
    @FXML
    private Label cardsInDeckLabel;
    @FXML
    private Label discardedCardsLabel;
    @FXML
    private Label damageCardsLabel;
    @FXML
    private Label energyCubeLabel;


    //Dynamic text values
    @FXML
    private Label userNameValue;
    @FXML
    private Label robotValue;
    @FXML
    private Label checkPointValue;
    @FXML
    private Label cardsInDeckValue;
    @FXML
    private Label discardedCardsValue;
    @FXML
    private Label damageCardsValue;
    @FXML
    private Label energyCubesValue;


    private StackPane[] registers;
    private int registerForAnimation = 0;
    private int currentRegisterActiveCount = 0;


    @FXML
    public void initialize() {
        userNameValue.textProperty().bindBidirectional(playerMatModel.getUserName());
        robotValue.textProperty().bindBidirectional(playerMatModel.getRobotName());
        checkPointValue.textProperty().bindBidirectional(playerMatModel.getCheckpointsreached());
        cardsInDeckValue.textProperty().bindBidirectional(playerMatModel.getDeckCount());
        discardedCardsValue.textProperty().bindBidirectional(playerMatModel.getDiscardedCount());
        damageCardsValue.textProperty().bindBidirectional(playerMatModel.getPickedUpDamageCards());
        energyCubesValue.textProperty().bindBidirectional(playerMatModel.getEnergyPoints());

        //Controller-Specific actions
        registers = new StackPane[5];
        registerForAnimation = 0;
        createCardsSlots();
    }

    public PlayerMatModel getPlayerMatModel() {
        return playerMatModel;
    }

    public void createCardsSlots(){

            for (int i = 0; i < 5; i++) {
                ImageView cardBackImage =  ImageBuilder.adjustToPlayerMatView("CardBack");
                Button button = new Button();
                button.setDisable(true);
                button.setVisible(false);
                button.setOnAction(e -> sendPlayIt(button));
                registers[i] = new StackPane();
                registers[i].getChildren().addAll(cardBackImage, button);
                playerMatPane.add(registers[i], i+1, 0);
            }

    }

    public GridPane getPlayerMat(){
        return playerMatPane;
    }

    public void setTakenRegister(String card) {
        ImageView cardToDisplay = ImageBuilder.adjustToPlayerMatView(card);
        Button button = (Button) registers[registerForAnimation].getChildren().get(1);
        button.setGraphic(cardToDisplay);
        registerForAnimation++;
    }

    private void sendPlayIt(Button button) {
        button.setDisable(true);
        button.setVisible(false);
        playerMatModel.sendPlayIt();
    }

    public void activateRegisterButton() {
        Button button = (Button) registers[currentRegisterActiveCount].getChildren().get(1);
        button.setDisable(false);
        button.setVisible(true);
        currentRegisterActiveCount++;
    }

    public void resetRegisterCounts() {
        currentRegisterActiveCount = 0;
        registerForAnimation = 0;
    }
}
