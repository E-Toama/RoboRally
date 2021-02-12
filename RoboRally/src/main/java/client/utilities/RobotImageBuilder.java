package client.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Doc: Yashar
 */
public class RobotImageBuilder {

    private static int tileWidth = 45;

    public static ImageView adjustToBoard(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(tileWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }


    public static ImageView buildRobotImage(int robotFigure) {


        if (robotFigure == 0) {
            Image botImage = new Image("Images/HammerBot.png");
            return adjustToBoard(botImage);
        } else if (robotFigure == 1) {
            Image botImage = new Image("Images/HulkX90.png");
            return adjustToBoard(botImage);
        } else if (robotFigure == 2) {
            Image botImage = new Image("Images/SmashBot.png");
            return adjustToBoard(botImage);
        } else if (robotFigure == 3) {
            Image botImage = new Image("Images/SpinBot.png");
            return adjustToBoard(botImage);
        }else if (robotFigure == 4) {
            Image botImage = new Image("Images/Twonky.png");
            return adjustToBoard(botImage);
        }else if (robotFigure == 5) {
            Image botImage = new Image("Images/ZoomBot.png");
            return adjustToBoard(botImage);
        } else {
            ImageView transparent = adjustToBoard(new Image("Tiles/Transparent_Tile.png"));
            return transparent;
        }
    }

    public static Image buildWinnerImage(int robotFigure) {

        if (robotFigure == 0) {
            Image botImage = new Image("Images/Purple.png");
            return botImage;
        } else if (robotFigure == 1) {
            Image botImage = new Image("Images/Red.png");
            return botImage;
        } else if (robotFigure == 2) {
            Image botImage = new Image("Images/Yellow.png");
            return botImage;
        } else if (robotFigure == 3) {
            Image botImage = new Image("Images/Blue.png");
            return botImage;
        }else if (robotFigure == 4) {
            Image botImage = new Image("Images/Orange.png");
            return botImage;
        }else if (robotFigure == 5) {
            Image botImage = new Image("Images/Green.png");
            return botImage;
        } else {
            Image transparent = new Image("Tiles/Transparent_Tile.png");
            return transparent;

        }
    }

    public static Color getRobotColor(int robotFigure) {
        if (robotFigure == 0) {
            return Color.web("#68369B");
        } else if (robotFigure == 1) {
            return Color.web("#B02318");
        } else if (robotFigure == 2) {
            return Color.web("#DD8F03");
        } else if (robotFigure == 3) {
            return Color.web("#324B84");
        }else if (robotFigure == 4) {
            return Color.web("#DA8043");
        }else if (robotFigure == 5) {
            return Color.web("#3F562A");
        } else {
            return Color.BLACK;
        }
    }
}
