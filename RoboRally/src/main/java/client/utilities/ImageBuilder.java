package client.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageBuilder {

    private static final int TILE_WIDTH = 50;
    private static final int PROGRAMMING_CARD_WIDTH = 80;
    private static final int PLAYERMAT_CARD_HEIGHT = 180;
    private static final int ENEMYPLAYERMAT_CARD_HEIGHT = 78;


    private static ImageView adjustToBoard(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(TILE_WIDTH);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static ImageView adjustToProgrammingView(String cardString) {
        ImageView imageView = createCardImageView(cardString);
        imageView.setFitWidth(PROGRAMMING_CARD_WIDTH);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static ImageView adjustToPlayerMatView(String cardString) {
        ImageView imageView = createCardImageView(cardString);
        imageView.setFitHeight(PLAYERMAT_CARD_HEIGHT);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static ImageView adjustToEnemyMatView(String cardString) {
        ImageView imageView = createCardImageView(cardString);
        imageView.setFitHeight(ENEMYPLAYERMAT_CARD_HEIGHT);
        imageView.setPreserveRatio(true);
        return imageView;
    }


    public static ImageView createCardImageView(String cardType) {
        Image cardImage;
        switch (cardType) {
            case "MoveOne":
                cardImage = new Image("Cards/MoveOne.png");
                return new ImageView(cardImage);
            case "MoveTwo":
                cardImage = new Image("Cards/MoveTwo.png");
                return new ImageView(cardImage);
            case "MoveThree":
                cardImage = new Image("Cards/MoveThree.png");
                return new ImageView(cardImage);
            case "LeftTurn":
                cardImage = new Image("Cards/LeftTurn.png");
                return new ImageView(cardImage);
            case "RightTurn":
                cardImage = new Image("Cards/RightTurn.png");
                return new ImageView(cardImage);
            case "U-Turn":
                cardImage = new Image("Cards/UTurn.png");
                return new ImageView(cardImage);
            case "BackUp":
                cardImage = new Image("Cards/MoveBack.png");
                return new ImageView(cardImage);
            case "PowerUp":
                cardImage = new Image("Cards/PowerUp.png");
                return new ImageView(cardImage);
            case "Again":
                cardImage = new Image("Cards/Again.png");
                return new ImageView(cardImage);
            case "Virus":
                cardImage = new Image("Cards/VirusImage.png");
                return new ImageView(cardImage);
            case "Worm":
                cardImage = new Image("Cards/WormImage.png");
                return new ImageView(cardImage);
            case "TrojanHorse":
                cardImage = new Image("Cards/TrojanHorseImage.png");
                return new ImageView(cardImage);
            case "Spam":
                cardImage = new Image("Cards/SpamImage.png");
                return new ImageView(cardImage);
            case "CardBack":
                cardImage = new Image("Cards/PlayerDeckBack.png");
                return new ImageView(cardImage);
            default:
                cardImage = new Image("Cards/PlayerDeckBack.png");
                return new ImageView(cardImage);
        }
    }

    public static ImageView buildImage(BoardElement boardElement) {

        GameBoardFieldObject[] fields = boardElement.getField();

        // If there is only one element to display (e.g. "Empty")
        if (fields.length == 1) {
            String type = fields[0].getType();
            GameBoardFieldObject gameBoardFieldObject = fields[0];

            switch (type) {
                case "Empty":
                    Image emptyTileImage = new Image("Tiles/EmptyTile.png");
                    return adjustToBoard(emptyTileImage);
                case "Laser":
                    LaserFieldObject laser = (LaserFieldObject) gameBoardFieldObject;
                    int count = laser.getCount();
                    String orientation = laser.getOrientation();
                    Image laserImage = new Image("Tiles/Laser_" + count + ".png");
                    ImageView laserTile = adjustToBoard(laserImage);
                    if (orientation.equals("left") || orientation.equals("right")) {
                        laserTile.setRotate(90);
                    }
                    return laserTile;

                case "Belt":
                    BeltFieldObject belt = (BeltFieldObject) gameBoardFieldObject;
                    String beltOrientation = belt.getOrientation();
                    Image belts = new Image("Tiles/Conveyor_" + belt.getSpeed() + ".png");
                    ImageView beltImage = adjustToBoard(belts);
                    switch (belt.getOrientation()) {
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
                    RotatingBeltFieldObject rotatingBelt = (RotatingBeltFieldObject) gameBoardFieldObject;
                    ImageView leftTurn = adjustToBoard(new Image("Tiles/ConveyorRotating_" + rotatingBelt.getSpeed() + "_left_" + rotatingBelt.isCrossing() + ".png"));
                    ImageView rightTurn = adjustToBoard(new Image("Tiles/ConveyorRotating_" + rotatingBelt.getSpeed() + "_right_" + rotatingBelt.isCrossing() + ".png"));

                    switch (rotatingBelt.getOrientations()[0]) {
                        case "right":
                            if ("down".equals(rotatingBelt.getOrientations()[1])) {
                                rightTurn.setRotate(180);
                                return rightTurn;
                            } else {
                                return leftTurn;
                            }
                        case "down":
                            if ("left".equals(rotatingBelt.getOrientations()[1])) {
                                rightTurn.setRotate(270);
                                return rightTurn;
                            } else {
                                leftTurn.setRotate(90);
                                return leftTurn;
                            }
                        case "left":
                            if ("down".equals(rotatingBelt.getOrientations()[1])) {
                                leftTurn.setRotate(180);
                                return leftTurn;
                            } else {
                                return rightTurn;
                            }
                        case "up":
                            if ("left".equals(rotatingBelt.getOrientations()[1])) {
                                leftTurn.setRotate(270);
                                return leftTurn;
                            } else {
                                rightTurn.setRotate(90);
                                return rightTurn;
                            }
                        default:
                            return adjustToBoard(new Image("Tiles/Laser_3.png"));
                    }
                case "EnergySpace": //TODO: create EnergySpaceImage with EnergyTokens and adjust name
                    EnergySpaceFieldObject energySpace = (EnergySpaceFieldObject) gameBoardFieldObject;
                    //Image EnergySpaceTile = new Image("Tiles/Energy_"+energySpace.getCount()+".png");
                    Image EnergySpaceTile = new Image("Tiles/Energy_0.png");
                    return adjustToBoard(EnergySpaceTile);

                case "StartPoint":
                    Image StartingPointTile = new Image("Tiles/StartingPoint.png");
                    return adjustToBoard(StartingPointTile);

                case "Wall":
                    Image WallTile = new Image("Tiles/Wall.png");
                    ImageView WallImage = adjustToBoard(WallTile);
                    switch (((WallFieldObject) fields[0]).getOrientations()[0]) {
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
                            return WallImage;
                    }

                case "ControlPoint":
                    Image checkImage = new Image("Tiles/Checkpoint_1.png");
                    return adjustToBoard(checkImage);

                case "RestartPoint":
                    Image restartImage = new Image("Tiles/RestartToken.png");
                    ImageView restartPoint = adjustToBoard(restartImage);
                    RestartPointFieldObject restartPointObject = (RestartPointFieldObject) gameBoardFieldObject;
                    switch (restartPointObject.getOrientation()) {
                        case "up":
                            restartPoint.setRotate(270);
                            return restartPoint;
                        case "down":
                            restartPoint.setRotate(90);
                            return restartPoint;
                        case "left":
                            restartPoint.setRotate(180);
                            return restartPoint;
                        default:
                            return restartPoint;
                    }

                case "Antenna":
                    Image antennaImage = new Image("Tiles/Antenna.png");
                    return adjustToBoard(antennaImage);

                case "Pit":
                    Image pitImage = new Image("Tiles/Pit.png");
                    return adjustToBoard(pitImage);

                case "Gear":
                    GearFieldObject gearFieldObject = (GearFieldObject) gameBoardFieldObject;
                    String gearOrientation = gearFieldObject.getOrientation();

                    if (gearOrientation.equals("right")) {
                        Image gearImageClockwise = new Image("Tiles/Gear_clockwise.png");
                        return adjustToBoard(gearImageClockwise);
                    } else  {
                        Image gearImageCounterClockwise = new Image("Tiles/Gear_counterclockwise.png");
                        return adjustToBoard(gearImageCounterClockwise);
                    }
            }

            // If there are two elements to display (e.g. "Wall and Laser")
        } else if (fields.length == 2) {
            /*
            *   Wall, Laser = LaserEnd mit Böbbl
            *   Wall, EnergyCube
                Belt, Laser
                Laser, Wall = LaserEnd ohne Böbbl
            * */
            if (fields[0] instanceof WallFieldObject) {
                if (fields[1] instanceof EnergySpaceFieldObject) {
                    //Default image has wall at bottom
                    Image wallWithEnergy = new Image("Tiles/Energy_withWall1.png");
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
                    Image wallWithLaser = new Image("Tiles/LaserWall_1.png");
                    ImageView wallWithLaserTile = adjustToBoard(wallWithLaser);
                    switch (((WallFieldObject) fields[0]).getOrientations()[0]) {
                        case "up":
                            wallWithLaserTile.setRotate(180);
                            return wallWithLaserTile;
                        case "left":
                            wallWithLaserTile.setRotate(90);
                            return wallWithLaserTile;
                        case "right":
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
                Image beltWithLaser = new Image("Tiles/ConveyorLaser_2.png");
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


            } else if (fields[0] instanceof LaserFieldObject) {
                //Default image: Belt upwards
                Image laserEnd = new Image("Tiles/LaserEnd_1.png");
                ImageView laserEndTile = adjustToBoard(laserEnd);
                switch (((WallFieldObject) fields[1]).getOrientations()[0]) {
                    case "up":
                        laserEndTile.setRotate(180);
                        return laserEndTile;
                    case "right":
                        laserEndTile.setRotate(270);
                        return laserEndTile;
                    case "left":
                        laserEndTile.setRotate(90);
                        return laserEndTile;
                    default:
                        return laserEndTile;
                }


            }


            else {
                //field[0] has wrong field type
            }


            // If there are three elements to display (e.g. Wall+Laser+Checkpoint)
        } else if (fields.length == 3) {

            try {
                WallFieldObject wallFieldObject = (WallFieldObject) fields[0];
                LaserFieldObject laserFieldObject = (LaserFieldObject) fields[1];
                ControlPointFieldObject controlPointFieldObject = (ControlPointFieldObject) fields[2];
                Image wallWithLaserAndCheckPoint = new Image("Tiles/WallLaser_CheckPoint" + controlPointFieldObject.getCount() + ".png");
                return adjustToBoard(wallWithLaserAndCheckPoint);


            } catch (ClassCastException e) {
                //ToDO: Catch Typecasting errors
            }


        } else {
            //Handle wrong length
        }


        return adjustToBoard(new Image("Tiles/Laser_3.png"));

    }
}
