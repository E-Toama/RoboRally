package client.view;


import client.viewmodel.EnemyMatModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;

import java.io.IOException;

public class EnemyMatController {

    EnemyMatModel enemyMatModel;

    @FXML
    GridPane enemyMat = new GridPane();

    int priority = 1;
    int checkPointCount = 0;
    int deckCardCount;
    String name = "Twonky";
    String userName = "Anton";
    String card1 = "Cards/Again.png";
    String card2 = "Cards/Again.png";
    String card3 = "Cards/Again.png";
    String card4 = "Cards/Again.png";
    String card5 = "Cards/Again.png";

    public EnemyMatController() {
        initialize();
    }

    public void setEnemyMatModel(EnemyMatModel enemyMatModel) {
        this.enemyMatModel = enemyMatModel;
    }


    @FXML
    public void initialize() {
        enemyMat.setBorder(new Border(new BorderStroke(Color.valueOf("AQUAMARINE"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
        createCardsSlots();
        Label priorityOrder = new Label("Priority: "+ priority) ;
        Label checkPoints= new Label("Checkpoints "+ checkPointCount);
        Label deckSize = new Label("Cards left: "+ deckCardCount);
        Label discardSize = new Label("Discarded Cards: "+ deckCardCount);
        Label RobotName = new Label(name);
        Label PlayerName = new Label(userName);
        enemyMat.add(setLabelMargin(checkPoints), 2, 1);
        //  PlayerMat.add(RobotName, 0, 1);
        enemyMat.add(setLabelMargin(PlayerName), 0, 1);
        enemyMat.add(setLabelMargin(deckSize), 3, 1);
        enemyMat.add(setLabelMargin(discardSize), 4, 1);
    }







    public void createCardsSlots(){
        for(int i = 0; i < 5; i++){

            Image cards = new Image(card1);
            ImageView card = new ImageView(cards);
            card.setFitHeight(83);
            card.setPreserveRatio(true);
            enemyMat.add(card, i, 0, 1, 1);
        }
    }

    private static Label setLabelMargin(Label label) {
        label.setPrefHeight(40);
        return label;
    }




    public GridPane getEnemyMat(){
        return enemyMat;
    }


}
