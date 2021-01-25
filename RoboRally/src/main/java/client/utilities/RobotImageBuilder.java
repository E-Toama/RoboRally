package client.utilities;

import game.Robots.HammerBot;
import game.Robots.Hulk;
import game.Robots.Robot;
import game.Robots.SmashBot;
import game.Robots.SpinBot;
import game.Robots.Twonky;
import game.Robots.ZoomBot;
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
}
