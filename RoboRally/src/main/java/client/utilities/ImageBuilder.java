package client.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.ControlPointFieldObject;
import game.gameboard.gameboardfieldobjects.EnergySpaceFieldObject;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboard.gameboardfieldobjects.LaserFieldObject;
import game.gameboard.gameboardfieldobjects.WallFieldObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.naming.ldap.ControlFactory;

/**
 * - Image-Dateien aus retajo.de einpflegen und umbenennen
 *  - Image-Builder:
 *  	- Cases implementieren
 *  	- Dateinamen anpassen
 *  - Handgephotoshoppte Bilder für CheckPoints von ExtraCrispy
 *
 *  Später:
 *  - GridPane in MainView einbinden
 *  - Roboterposition
 *  - Startpoints klickbar
 */

public class ImageBuilder {

    private static int tileWidth = 50;


    private static ImageView adjustToBoard(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(tileWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static ImageView buildImage(BoardElement boardElement) {

        GameBoardFieldObject[] fields = boardElement.getField();

        if (fields.length == 1) {
            String type = fields[0].getType();
            GameBoardFieldObject gameBoardFieldObject = fields[0];

            switch (type) {
                case "Empty":
                    Image emptyTileImage = new Image("Pfad zum Empty Image");
                    ImageView emptyTile = adjustToBoard(emptyTileImage);
                    return emptyTile;
                case "Laser":
                    LaserFieldObject laser = (LaserFieldObject) gameBoardFieldObject;
                    int count = laser.getCount();
                    String orientation = laser.getOrientation();

                    //TODO: Laser-image-file should be named like laser_1, laser_2, laser_3
                    Image laserImage = new Image("laser_"+count);

                    ImageView laserTile = adjustToBoard(laserImage);
                    if (orientation.equals("left") || orientation.equals("right")) {
                        laserTile.setRotate(90);
                    }
                    return laserTile;

                //TODO: IMPLEMENT remaining cases
                case "Belt":
                case "RotatingBelt":
                case "EnergySpace":
                case "StartingPoint":
                case "Wall":
                case "ControlPoint":
                case "RestartPoint":
                case "Antenna":
                case "Pit":
                case "Gear":
            }


        } else if (fields.length == 2) {
            /*
            *   Wall, Laser
            *   Wall, EnergyCube
                Belt, Laser
            * */
            if(fields[0] instanceof WallFieldObject) {
                if (fields[1] instanceof EnergySpaceFieldObject) {
                    //Default image has wall at bottom
                    Image wallWithEnergy = new Image("Pfad zur Wall with Energy 1");
                    ImageView wallWithEnergyTile = adjustToBoard(wallWithEnergy);
                    switch (((WallFieldObject) fields[0]).getOrientations()[0]) {
                        case "up":
                            wallWithEnergyTile.setRotate(180);
                            return wallWithEnergyTile;
                        case "left":
                            wallWithEnergyTile.setRotate(90);
                            return wallWithEnergyTile;
                        case "right":
                            wallWithEnergyTile.setRotate(270);
                            return wallWithEnergyTile;
                        default:
                            return wallWithEnergyTile;
                    }

                } else if (fields[1] instanceof LaserFieldObject) {
                    //default direction is: Wall up, Laser pointing down
                    Image wallWithLaser = new Image("Pfad zur Wall with Laser 1");
                    ImageView wallWithLaserTile = adjustToBoard(wallWithLaser);
                    switch (((WallFieldObject) fields[0]).getOrientations()[0]) {
                        case "down":
                            wallWithLaserTile.setRotate(180);
                            return wallWithLaserTile;
                        case "right":
                            wallWithLaserTile.setRotate(90);
                            return wallWithLaserTile;
                        case "left":
                            wallWithLaserTile.setRotate(270);
                            return wallWithLaserTile;
                        default:
                            return wallWithLaserTile;
                    }


                } else {
                    //fields[1] has wrong field type
                }
            } else if (fields[0] instanceof BeltFieldObject) {
                //Default image: Belt upwards
                Image beltWithLaser = new Image("Pfad zum LaserBelt");
                ImageView beltWithLaserTile = adjustToBoard(beltWithLaser);
                switch (((BeltFieldObject) fields[0]).getOrientation()) {
                    case "down":
                        beltWithLaserTile.setRotate(180);
                        return beltWithLaserTile;
                    case "left":
                        beltWithLaserTile.setRotate(270);
                        return beltWithLaserTile;
                    case "right":
                        beltWithLaserTile.setRotate(90);
                        return beltWithLaserTile;
                    default:
                        return beltWithLaserTile;
                }


            } else {
                //field[0] has wrong field type
            }




        } else if (fields.length == 3) {
            //ImageView: Wall+Laser+Checkpoint

            try {
                WallFieldObject wallFieldObject = (WallFieldObject) fields[0];
                LaserFieldObject laserFieldObject = (LaserFieldObject) fields[1];
                ControlPointFieldObject controlPointFieldObject = (ControlPointFieldObject) fields[2];
                //TODO: Create Laser-Wall-Checkpoint-images (GIMP / Photoshop) customized to ExtraCrispy
                //TODO: Laser-Wall-image-file should be named like laserwallcheck_1, laserwallcheck_2, laserwallcheck_3, laserwallcheck_4
                Image wallWithLaserAndCheckPoint = new Image("laserwallcheck_"+controlPointFieldObject.getCount());
                ImageView wallLaserCheckTile = adjustToBoard(wallWithLaserAndCheckPoint);
                return wallLaserCheckTile;


            } catch (ClassCastException e) {
                //ToDO: Catch Typecasting errors
            }




        } else {
            //Handle wrong length
        }

        for (GameBoardFieldObject gbfo : fields) {
            String type = gbfo.getType();

            switch (type) {
                case "Empty":
                    Image emptyTileImage = new Image("Pfad zum Empty Image");
                    ImageView emptyTile = new ImageView(emptyTileImage);
                    emptyTile.setFitHeight(50);
                    emptyTile.setPreserveRatio(true);
                    return emptyTile;
                case "Laser":
                    LaserFieldObject laser = (LaserFieldObject) gbfo;
                    int count = laser.getCount();
                    String orientation = laser.getOrientation();
                    Image laserImage = new Image("Pfad zum LaserImage");
                    ImageView laserTile = new ImageView(laserImage);
                    laserTile.setFitHeight(50);

                    switch (orientation) {
                        case "up":
                            laserTile.setRotate(90);
                            return laserTile;
                        case "down":
                            laserTile.setRotate(270);
                            return laserTile;
                    }

            }

        }

        return null;

    }

}
