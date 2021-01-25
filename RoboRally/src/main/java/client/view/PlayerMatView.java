package client.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PlayerMatView {
    @FXML
    GridPane playerMatPane = new GridPane();
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


    public void initialize() {
        createCardsSlots();

        Label checkPoints= new Label("Checkpoints "+ checkPointCount);
        Label deckSize = new Label("Cards left: "+ deckCardCount);
        Label discardSize = new Label("Discarded Cards: "+ deckCardCount);
        Label RobotName = new Label(name);
        Label PlayerName = new Label(userName);

        playerMatPane.add(checkPoints, 1, 0);
        playerMatPane.add(RobotName, 0, 0);
        //PlayerMat.add(PlayerName, 0, 0);
        playerMatPane.add(deckSize, 2, 0);
        playerMatPane.add(discardSize, 3, 0);
    }

    public PlayerMatView() {
      initialize();



    }   public void createCardsSlots(){
        for(int i = 0; i < 5; i++){

            Image cards = new Image(card1);
            ImageView card = new ImageView(cards);
            card.setFitHeight(180);
            card.setPreserveRatio(true);
            playerMatPane.add(card, 1+i, 0, 1, 4);
        }
    }




    public GridPane getPlayerMat(){
        return playerMatPane;
    }

}
