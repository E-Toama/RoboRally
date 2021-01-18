package client.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardTile extends StackPane {

    public BoardTile(String content, double x, double y, double width, double height) {
        //Creating an image
        Image image = null;
        try {
            image = new Image(new FileInputStream("/home/ada/IdeaProjects/vp-neidische-narwale/RoboRally/src/main/resources/GraphicsUniveristy/SingleBoardTile.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(x);
        imageView.setY(y);

        //setting the fit height and width of the image view
        //imageView.setFitHeight(455);
        //imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);
        // create label
        Label label = new Label(content);
        label.setPrefWidth(width);
        label.setPrefHeight(height);

        // set position
        setTranslateX(x);
        setTranslateY(y);

        getChildren().addAll(root, label);

    }

}
