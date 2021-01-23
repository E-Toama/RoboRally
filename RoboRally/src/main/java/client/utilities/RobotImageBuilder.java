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

    public static ImageView buildRobotImage(BoardElement boardElement) {

        Robot robot = boardElement.getRobot();

        if (robot instanceof Twonky) {
            Image botImage = new Image("Images/Twonky_transparent.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot instanceof SmashBot) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot instanceof Hulk) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else if (robot instanceof SpinBot) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        }else if (robot instanceof ZoomBot) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        }else if (robot instanceof HammerBot) {
            Image botImage = new Image("Images/Orange.png");
            ImageView botImageView = adjustToBoard(botImage);
            return botImageView;
        } else {
            ImageView transparent = adjustToBoard(new Image("Tiles/Transparent_Tile.png"));
            return transparent;
        }




    }




}
