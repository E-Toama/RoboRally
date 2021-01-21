package client.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.*;
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
                    Image emptyTileImage = new Image("Tiles/EmptyTile.png");
                    ImageView emptyTile = adjustToBoard(emptyTileImage);
                    return emptyTile;
                case "Laser":
                    LaserFieldObject laser = (LaserFieldObject) gameBoardFieldObject;
                    int count = laser.getCount();
                    String orientation = laser.getOrientation();

                    //TODO: Laser-image-file should be named like laser_1, laser_2, laser_3
                    Image laserImage = new Image("Tiles/Laser_"+count);

                    ImageView laserTile = adjustToBoard(laserImage);
                    if (orientation.equals("left") || orientation.equals("right")) {
                        laserTile.setRotate(90);
                    }
                    return laserTile;

                //TODO: IMPLEMENT remaining cases
                case "Belt":
                    BeltFieldObject belt = (BeltFieldObject) gameBoardFieldObject;
                    String beltOrientation = belt.getOrientation();
                    Image belts = new Image("Tiles/Conveyor_"+ ((BeltFieldObject) fields[1]).getSpeed()+".png");
                    ImageView beltImage = adjustToBoard(belts);
                    switch  (((BeltFieldObject) fields[0]).getOrientation()) {
                        case "down":
                            beltImage.setRotate(90);
                            return beltImage;
                        case "left":
                            beltImage.setRotate(180);
                            return beltImage;
                        case "up":
                            beltImage.setRotate(270);
                            return beltImage;
                        default:
                            return beltImage;
                    }

                case "RotatingBelt":
                    Image RotatingBeltTile = new Image("Tiles/ConveyorRotating_"+((RotatingBeltFieldObject) fields[0]).getSpeed()+ "_" +((RotatingBeltFieldObject) fields[2]).getOrientations()[1]+"_"+((RotatingBeltFieldObject) fields[1]).isCrossing()+".png");
                    ImageView RotatingBeltImage = adjustToBoard(RotatingBeltTile);
                    switch (((RotatingBeltFieldObject) fields[2]).getOrientations()[0]){
                        case "right":
                            RotatingBeltImage.setRotate(90);
                            return RotatingBeltImage;
                        case "down":
                            RotatingBeltImage.setRotate(180);
                            return RotatingBeltImage;
                        case "left":
                            RotatingBeltImage.setRotate(270);
                            return RotatingBeltImage;
                        default:
                            return RotatingBeltImage;
                    }
                case "EnergySpace": //TODO: create EnergySpaceImage without EnergyTokens and adjust name
                    Image EnergySpaceTile = new Image("Tiles/Energy_"+((EnergySpaceFieldObject) fields[0]).getCount()+".png");
                    ImageView EnergySpaceImage = adjustToBoard(EnergySpaceTile);
                    return EnergySpaceImage;

                case "StartingPoint":
                    Image StartingPointTile = new Image("Tiles/StartingPoint.png");
                    ImageView StartingPointImage = adjustToBoard(StartingPointTile);
                    return  StartingPointImage;

                case "Wall":
                    Image WallTile = new Image("Tiles/Wall.png");
                    ImageView WallImage = adjustToBoard(WallTile);
                    switch (((WallFieldObject) fields[0]).getOrientations()[0]){
                        case "right":
                            WallImage.setRotate(90);
                            return WallImage;
                        case "down":
                            WallImage.setRotate(180);
                            return WallImage;
                        case "left":
                            WallImage.setRotate(270);
                            return WallImage;
                        default:
                            return  WallImage;
                    }

                case "ControlPoint":
                    Image checkImage = new Image("Tiles/Checkpoint_1.png");
                    ImageView checkImageView = adjustToBoard(checkImage);
                    return checkImageView;

                case "RestartPoint":
                    Image restartImage = new Image("Tiles/RestartToken.png");
                    ImageView restartPoint = adjustToBoard(restartImage);
                    return restartPoint;

                case "Antenna":
                    Image antennaImage = new Image("Tiles/Antenna.png");
                    ImageView antenna = adjustToBoard(antennaImage);
                    return antenna;

                case "Pit":
                    Image pitImage = new Image("Tiles/Pit.png");
                    ImageView pit = adjustToBoard(pitImage);
                    return pit;

                case "Gear":
                    GearFieldObject gearFieldObject = (GearFieldObject) gameBoardFieldObject;
                    String gearOrientation = gearFieldObject.getOrientation();

                    if (gearOrientation.equals("clockwise")) {
                        Image gearImageClockwise = new Image("Tiles/Gear_clockwise.png");
                        ImageView gearClockWise = adjustToBoard(gearImageClockwise);
                        return gearClockWise;
                    }
                    else if (gearOrientation.equals("counterclockwise")) {
                        Image gearImageCounterClockwise = new Image("Tiles/Gear_counterclockwise.png");
                        ImageView gearCounterClockwise = adjustToBoard(gearImageCounterClockwise);
                        return gearCounterClockwise;
                    }

                    else { //evtl Fehlerbehebung?

                    }
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
                    Image wallWithEnergy = new Image("Tiles/Energy_withWall1");
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
                    Image wallWithLaser = new Image("Tiles/LaserWall_1");
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
                Image beltWithLaser = new Image("Tiles/ConveyorLaser_2");
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
                Image wallWithLaserAndCheckPoint = new Image("Antenna.png"+controlPointFieldObject.getCount());
                ImageView wallLaserCheckTile = adjustToBoard(wallWithLaserAndCheckPoint);
                return wallLaserCheckTile;


            } catch (ClassCastException e) {
                //ToDO: Catch Typecasting errors
            }




        } else {
            //Handle wrong length
        }



        return null;

    }

}
