package client.view;


import client.utilities.ImageBuilder;
import client.viewmodel.PlayerMatModel;
import game.Robots.Robot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
    private Label CardsInDeckLabel;
    @FXML
    private Label DiscardedCardsLabel;
    @FXML
    private Label damageCardsLabel;
    @FXML
    private Label EnergyCubeLabel;


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


    Button[] registers;
    int registerForAnimation = 0;

    int currentRegisterActiveCount = 0;

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
        registers = new Button[5];
        registerForAnimation = 0;
        createCardsSlots();
    }

    public PlayerMatModel getPlayerMatModel() {
        return playerMatModel;
    }

    public void createCardsSlots(){

            for (int i = 0; i < 5; i++) {
                Button button = new Button();
                ImageView cardBackImage =  ImageBuilder.adjustToPlayerMatView("CardBack");

                button.setGraphic(cardBackImage);
                button.setDisable(true);
                button.setOnAction(e -> sendPlayIt(button));

                registers[i] = button;

                playerMatPane.add(registers[i], i+1, 0);
            }

    }

    public GridPane getPlayerMat(){
        return playerMatPane;
    }

    public void setTakenRegister(String card) {
        ImageView cardToDisplay = ImageBuilder.adjustToPlayerMatView(card);
        //ToDO: FX-Transition? FlipCard-Animation
        Button button = registers[registerForAnimation];
        button.setGraphic(cardToDisplay);
        registers[registerForAnimation] = button;
        //playerMatPane.add(registers[registerForAnimation], registerForAnimation+1, 0);
        registerForAnimation++;
    }

    private void sendPlayIt(Button button) {
            button.setDisable(true);
            playerMatModel.sendPlayIt();
    }

    public void activateRegisterButton() {
        Button button = registers[currentRegisterActiveCount];
        button.setDisable(false);
        registers[currentRegisterActiveCount] = button;
        currentRegisterActiveCount++;
    }
}
