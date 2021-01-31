package client.view;

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


public class MapChoiceDialog {

    private Stage stage;
    private String userChoice = "";

    public void show(String[] availableMaps) {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose Track");
        stage.setWidth(500);


        VBox outerFrame = new VBox();
        outerFrame.setAlignment(Pos.CENTER);

        Label label = new Label("Select track:");
        label.setFont(new Font(30));
        label.setPadding(new Insets(10));
        label.setAlignment(Pos.CENTER);

        outerFrame.getChildren().add(label);

        GridPane trackDisplay = new GridPane();
        trackDisplay.setAlignment(Pos.CENTER);

        int columnCounter = 0;

        for (String track : availableMaps) {
            VBox singleTrack = new VBox();
            singleTrack.setSpacing(10);
            singleTrack.setPadding(new Insets(20));
            singleTrack.setAlignment(Pos.CENTER);
            Button button = new Button(track);
            button.setId(track);
            button.setPadding(new Insets(10));
            button.setOnAction(e -> submitTrack(button));

            ImageView thumbnail = new ImageView(new Image("Tiles/" + track + "Thumbnail.png"));
            thumbnail.setFitWidth(200);
            thumbnail.setPreserveRatio(true);
            singleTrack.getChildren().addAll(button, thumbnail);
            trackDisplay.add(singleTrack, columnCounter, 0);
            columnCounter++;
        }

        outerFrame.getChildren().add(trackDisplay);

        Scene scene = new Scene(outerFrame);
        stage.setScene(scene);
        stage.showAndWait();

    }

    private void submitTrack(Button button) {
        stage.close();
        userChoice = button.getId();
    }

    public String getUserChoice() {
        return userChoice;
    }
}
