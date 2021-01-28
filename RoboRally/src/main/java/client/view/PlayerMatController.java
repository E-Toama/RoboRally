package client.view;


import client.viewmodel.PlayerMatModel;
import game.Robots.Robot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PlayerMatController {

    private PlayerMatModel playerMatModel;

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
    private Label userNameValue = new Label();
    @FXML
    private Label robotValue = new Label();
    @FXML
    private Label checkPointValue = new Label();
    @FXML
    private Label cardsInDeckValue = new Label();
    @FXML
    private Label discardedCardsValue = new Label();
    @FXML
    private Label damageCardsValue = new Label();
    @FXML
    private Label energyCubesValue = new Label();


    String cardBack = "Cards/PlayerDeckBack.png";


    public void initializePlayerMatView() throws IOException {
        FXMLLoader playerMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/PlayerMat.fxml"));
        playerMatPane = playerMatLoader.load();
        createCardsSlots();
    }

    public void setPlayerMatModel(PlayerMatModel playerMatModel) {
        this.playerMatModel = playerMatModel;
    }

    public void updateLabels() {
        userNameValue.setText(playerMatModel.getPlayerState().getUserName());
        robotValue.setText(String.valueOf(Robot.getRobotByFigure(playerMatModel.getPlayerState().getFigure())));
        checkPointValue.setText(String.valueOf(playerMatModel.getPlayerState().getCheckpointsreached()));
        cardsInDeckValue.setText(String.valueOf(playerMatModel.getPlayerState().getDeckCount()));
        discardedCardsValue.setText(String.valueOf(playerMatModel.getPlayerState().getDiscardedCount()));
        damageCardsValue.setText(String.valueOf(playerMatModel.getPlayerState().getPickedUpDamageCards()));
        energyCubesValue.setText(String.valueOf(playerMatModel.getPlayerState().getEnergyPoints()));
        
    }

    public void createCardsSlots(){
        for(int i = 0; i < 5; i++){

            Image cards = new Image(cardBack);
            ImageView card = new ImageView(cards);
            card.setFitHeight(180);
            card.setPreserveRatio(true);
            playerMatPane.add(card, 1+i, 0);
        }
    }

    public GridPane getPlayerMat(){
        return playerMatPane;
    }

}
