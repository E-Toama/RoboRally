package client.view;


import client.utilities.ImageBuilder;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.PlayerMatModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import client.viewmodel.EnemyMatModel;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;

import java.io.IOException;

public class EnemyMatController {


    private EnemyMatModel enemyMatModel = new EnemyMatModel(this);

    @FXML
    private GridPane enemyMatPane;

    @FXML
    private GridPane enemyMat;

    //Dynamic text values
    @FXML
    private Label userNameValue;
    @FXML
    private Label checkPointValue;

    @FXML
    private Label damageCardValue;

    @FXML
    private Label energyCubeValue;


    private StackPane[] registers;
    private int registerForAnimation = 0;


    @FXML
    public void initialize() {
        userNameValue.textProperty().bindBidirectional(enemyMatModel.getUserName());
        checkPointValue.textProperty().bindBidirectional(enemyMatModel.getCheckpointsreached());
        damageCardValue.textProperty().bindBidirectional(enemyMatModel.getPickedUpDamageCards());
        energyCubeValue.textProperty().bindBidirectional(enemyMatModel.getEnergyPoints());


        //Controller-Specific actions
        registers = new StackPane[5];
        registerForAnimation = 0;
        createCardsSlots();

    }

    public void createCardsSlots(){

        for(int i = 0; i < 5; i++){
            StackPane cardSlot = new StackPane();
            ImageView card =  ImageBuilder.adjustToEnemyMatView("CardBack");
            cardSlot.getChildren().add(card);
            registers[i] = cardSlot;
            enemyMat.add(registers[i], i, 0);
        }
    }

    public EnemyMatModel getEnemyMatModel() {
        return enemyMatModel;
    }

    public GridPane getEnemyMat(){
        return enemyMatPane;
    }

    public void setTakenRegister(String card) {
        ImageView cardToDisplay = ImageBuilder.adjustToEnemyMatView(card);
        registers[registerForAnimation].getChildren().add(cardToDisplay);
        registerForAnimation++;
    }

    public void resetRegisterCounts() {
        registerForAnimation = 0;
        for (int i = 0; i < 5; i++) {
            if (registers[i].getChildren().size() > 1) {
                registers[i].getChildren().remove(1);
            }
        }
    }


}
