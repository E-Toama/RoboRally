package client.view;

import game.gameboard.FieldElement;
import game.gameboard.boardelements.BoardElement;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardTile extends StackPane {

    public BoardTile(BoardElement boardElement, double x, double y, double width, double height) {
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


        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        // create label
        Label label = new Label(toConcatenatedString(boardElement));
        label.setPrefWidth(width);
        label.setPrefHeight(height);

        // set position
        setTranslateX(x);
        setTranslateY(y);

        getChildren().addAll(root, label);

    }

    /**
     * Preliminary helper-function that creates a label text for each BoardElement.
     * TODO: Replace with actual visual depiction of BoardElements
     * @param boardElement the BoardElement to be visualized
     * @return String for Label-text
     */
    public String toConcatenatedString(BoardElement boardElement) {
        String result = "";
        for (FieldElement fe : boardElement.getFields()) {
            result += fe.getType() + "\n";
        }
        return result;
    }

}
