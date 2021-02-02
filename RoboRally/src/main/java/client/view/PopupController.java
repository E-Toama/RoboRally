package client.view;

import client.network.ClientThread;
import client.utilities.ImageBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

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

    public PopupController() {
        clientThread = ClientThread.getInstance();

        //Init empty stage
        stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);

        //Init outer container
        popupContainer = new VBox();
        popupContainer.setPrefWidth(600);
        popupContainer.setAlignment(Pos.CENTER);

        //init title label
        titleLabel = new Label();
        titleLabel.setFont(new Font(30));
        titleLabel.setPadding(new Insets(10));
        titleLabel.setAlignment(Pos.CENTER);

        contentLabel = new Label();
        contentLabel.setPadding(new Insets(10));

        contentContainer = new GridPane();
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setHgap(10);

        popupContainer.getChildren().addAll(titleLabel, contentLabel, contentContainer);

    }

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

    public void showEndOfProgrammingPhase(String slowPlayers, String[] yourCards) {

        titleLabel.setText("Timer ran out!");
        contentLabel.setText(slowPlayers);

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
     * Submit Track in MapChoice-Popup
     * @param button
     */
    private void submitTrack(Button button) {
        stage.close();
        //userChoice = button.getId();
        String[] selectedMap = {button.getId()};
        clientThread.sendSelectedMap(selectedMap);
    }

    /**
     * Submit Chosen DamageCard
     * @param button
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
