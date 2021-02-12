package client.view;

import client.network.ClientThread;
import client.utilities.ImageBuilder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Controller class for the chooseTrack, timerEnded and pickDamage popups
 * 
 */
public class PopupController {

    private ClientThread clientThread;
    private Stage stage;
    private VBox popupContainer;
    private Label titleLabel;
    private Label contentLabel;
    private GridPane contentContainer;

    private double damageCardButtonWidth = 150;
    private double mapButtonWidth = 250;
    private double programmingCardButtonWidth = 80;

    //Instance Variables for PickDamage
    private ArrayList<String> clickedButtons;
    private String[] userChoice;
    private int count;
    private int cardsLeftToPick;

    /**
     * Constructor for PopupController with client thread
     * initializes empty stage and outer container of the popups
     * initializes popup attributes
     *
     */
    public PopupController() {
        clientThread = ClientThread.getInstance();

        stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);

        popupContainer = new VBox();



        titleLabel = new Label();
        contentLabel = new Label();
        contentContainer = new GridPane();
        popupContainer.getChildren().addAll(titleLabel, contentLabel, contentContainer);

        //Adding stylesheet.css
        popupContainer.getStylesheets().add("FXMLFiles/stylesheet.css");
        popupContainer.setId("popupContainer");
        titleLabel.setId("titleLabel");
        contentLabel.setId("contentLabel");
        contentContainer.setId("contentContainer");
    }

    /**
     * shows chooseTrack / mapChoice popup
     * @param availableMaps are Dizzy Highway and Extra Crsipy
     */
    public void showMapChoice(String[] availableMaps) {

        titleLabel.setText("Choose Track");
        contentLabel.setText("Dizzy Highway is good for beginners");

        int columnCounter = 0;

        for (String track : availableMaps) {
            Button button = new Button();
            button.setPadding(new Insets(10));
            button.setId(track);
            ImageView trackThumbNail = new ImageView(new Image("Tiles/" + track + "Thumbnail.png"));
            trackThumbNail.setFitWidth(mapButtonWidth);
            trackThumbNail.setPreserveRatio(true);
            button.setGraphic(trackThumbNail);
            button.setOnAction(e -> submitTrack(button));
            contentContainer.add(button, columnCounter, 0);
            columnCounter++;
        }


        Scene scene = new Scene(popupContainer);
        stage.setScene(scene);
        stage.showAndWait();



    }

    /**
     * shows pickDamage popups
     * @param count is the amount of damage cards which has to be chosen
     * @param availableDamageCards are the shown damage cards
     */
    public void showPickDamage(int count, LinkedList<String> availableDamageCards) {

        this.count = count;
        cardsLeftToPick = count;
        clickedButtons = new ArrayList<>();
        userChoice = new String[count];

        titleLabel.setText("Pick your Poison:");

        contentLabel.setText("Cards you still need to select: " + cardsLeftToPick);


        int columnCounter = 0;

        for (String card : availableDamageCards) {
            Button button = new Button();
            button.setId(card);
            button.setPadding(new Insets(10));
            button.setOnAction(e -> submitCard(button));

            ImageView thumbnail = new ImageView(new Image("Cards/" + card + "Image.png"));
            thumbnail.setFitWidth(damageCardButtonWidth);
            thumbnail.setPreserveRatio(true);
            button.setGraphic(thumbnail);
            contentContainer.add(button, columnCounter, 0);
            columnCounter++;
        }

        Scene scene = new Scene(popupContainer);
        stage.setScene(scene);
        stage.showAndWait();

    }

    /**
     * shows timerEnd popup
     * @param slowPlayers are the players who didn't finish before the timer ended
     * @param yourCards are the programming cards
     */
    public void showEndOfProgrammingPhase(String slowPlayers, String[] yourCards) {

        titleLabel.setText("Timer ended!");
        if (!slowPlayers.isEmpty()) {
            contentLabel.setText("Way too slow:\n" + slowPlayers + "\nThese are your cards: ");
        } else {
            contentLabel.setText("These are your cards: ");
        }


        int columnCounter = 0;

        for (String card : yourCards) {
            ImageView cardImage = ImageBuilder.adjustToProgrammingView(card);
            contentContainer.add(cardImage, columnCounter, 0);
            columnCounter++;
        }

        Scene scene = new Scene(popupContainer);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * shows errorMesssage pop up
     * @param errorMessage is an  error
     */
    public void showErrorMessage(String errorMessage) {
        titleLabel.setText("An Error occurred...");
        contentLabel.setText(errorMessage);
        Scene scene = new Scene(popupContainer);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * submits Track in MapChoice-Popup
     * @param button is a track button
     */
    private void submitTrack(Button button) {
        stage.close();
        //userChoice = button.getId();
        String[] selectedMap = {button.getId()};
        clientThread.sendSelectedMap(selectedMap);
    }

    /**
     * submits Chosen DamageCard
     * @param button is a card button
     */
    private void submitCard(Button button) {
        button.setDisable(true);
        cardsLeftToPick--;
        contentLabel.setText("Cards you still need to select: " + cardsLeftToPick);
        clickedButtons.add(button.getId());
        if (clickedButtons.size() == count) {
            userChoice = clickedButtons.toArray(new String[0]);
            stage.close();
            clientThread.sendSelectedDamage(userChoice);
        }
    }






}
