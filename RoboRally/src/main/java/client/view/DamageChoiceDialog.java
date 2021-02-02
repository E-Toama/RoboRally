package client.view;

import client.network.ClientThread;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;


public class DamageChoiceDialog {

    private ClientThread clientThread;
    private Stage stage;
    private ArrayList<String> clickedButtons;
    private String[] userChoice;
    private int count;
    private int cardsLeftToPick;
    private Label leftToPick;


    public void show(int count, LinkedList<String> availableDamageCards) {

        clientThread = ClientThread.getInstance();

        this.count = count;
        cardsLeftToPick = count;
        clickedButtons = new ArrayList<>();
        userChoice = new String[count];

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pick your Poison:");
        stage.setWidth(500);


        VBox outerFrame = new VBox();
        outerFrame.setAlignment(Pos.CENTER);

        Label heading = new Label("Pick your Poison:");
        heading.setFont(new Font(30));
        heading.setPadding(new Insets(10));
        heading.setAlignment(Pos.CENTER);

        outerFrame.getChildren().add(heading);

        leftToPick = new Label("Cards you still need to select: " + cardsLeftToPick);
        outerFrame.getChildren().add(leftToPick);

        GridPane trackDisplay = new GridPane();
        trackDisplay.setAlignment(Pos.CENTER);

        int columnCounter = 0;

        for (String card : availableDamageCards) {
            VBox singleTrack = new VBox();
            singleTrack.setSpacing(10);
            singleTrack.setPadding(new Insets(20));
            singleTrack.setAlignment(Pos.CENTER);
            Button button = new Button();
            button.setId(card);
            button.setPadding(new Insets(10));
            button.setOnAction(e -> submitCard(button));

            ImageView thumbnail = new ImageView(new Image("Cards/" + card + "Image.png"));
            thumbnail.setFitWidth(150);
            thumbnail.setPreserveRatio(true);
            button.setGraphic(thumbnail);
            singleTrack.getChildren().add(button);
            trackDisplay.add(singleTrack, columnCounter, 0);
            columnCounter++;
            singleTrack.setSpacing(10);
        }

        outerFrame.getChildren().add(trackDisplay);

        Scene scene = new Scene(outerFrame);
        stage.setScene(scene);
        stage.showAndWait();

    }

    private void submitCard(Button button) {
        button.setDisable(true);
        cardsLeftToPick--;
        leftToPick.setText("Cards you still need to select: " + cardsLeftToPick);
        clickedButtons.add(button.getId());
        if (clickedButtons.size() == count) {
            userChoice = clickedButtons.toArray(new String[0]);
            stage.close();
            clientThread.sendSelectedDamage(userChoice);
        }
    }

    public String[] getUserChoice() {
        return userChoice;
    }
}
