package client.utilities;

import game.Robots.Robot;
import game.gameboard.BoardElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RobotImageBuilder {

    private static int tileWidth = 45;

    public static ImageView adjustToBoard(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(tileWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static ImageView buildRobotImage(BoardElement boardElement) {

        Robot robot = boardElement.getRobot();

        if (robot.getFigure() == 4) {
            Image botImage = new Image("Images/Twonky_transparent.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot.getFigure() == 2) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot.getFigure() == 1) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot.getFigure() == 3) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        }else if (robot.getFigure() == 5) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        }else if (robot.getFigure() == 0) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else {
            ImageView transparent = adjustToBoard(new Image("Tiles/Transparent_Tile.png"));
            return transparent;
        }




    }




}
