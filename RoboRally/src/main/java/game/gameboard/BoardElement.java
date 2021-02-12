package game.gameboard;

import game.Game;
import game.robots.Robot;
import game.gameboard.gameboardfieldobjects.*;
import game.utilities.GameState;
import game.utilities.Position;
import game.utilities.PositionLookUp;

/**
 * This class represents a BoardElement with all its possible properties.
 * 
 */

public class BoardElement {

    private final int position;
    private final GameBoardFieldObject[] field;
    private int x;
    private int y;
    private Position xy;

    private Robot robot;

    public BoardElement(int position, GameBoardFieldObject[] field) {

        this.position = position;
        this.field = field;

        Position xy = PositionLookUp.positionToXY.get(position);
        this.x = xy.getX();
        this.y = xy.getY();
        this.xy = xy;

    }
    /**
     * Some Getters :
     */

    public GameBoardMapObject returnGameBoardMapObject() {
        return new GameBoardMapObject(position, field);
    }
    public Position getXY() {
        return xy;
    }

    public GameBoardFieldObject[] getField() {
        return field;
    }

    public Robot getRobot() {
        return robot;
    }


    public int getPosition() {
        return position;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * This method checks if there is a wall in front of the Robot
     * @param movingDirection
     * @return boolean
     */
    public boolean checkIfElementCanLeftInThisDirection(String movingDirection) {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Wall")) {

                WallFieldObject wallFieldObject = (WallFieldObject) fieldObject;

                for (String orientation : wallFieldObject.getOrientations()) {

                    if (orientation.equals(movingDirection)) {

                        return false;

                    }

                }

            }

        }

        return true;

    }

    /**
     * This method checks if there is a wall in front of the Robot
     * @param movingDirection
     * @return boolean
     */
    public boolean checkIfElementCanEnteredInThisDirection(String movingDirection) {

        String oppositeDirection = getOppositeDirection(movingDirection);

        return checkIfElementCanLeftInThisDirection(oppositeDirection);

    }

    /**
     * This method returns the opposite direction of your parameter.
     * @param movingDirection
     * @return opposite of moving Direction
     */
    public String getOppositeDirection(String movingDirection) {

        return switch (movingDirection) {
            case "up" ->  "down";
            case "left" -> "right";
            case "down" -> "up";
            case "right" -> "left";
            default -> null;
        };

    }

    /**
     * This method checkss if fieldObject is a Pit.
     * @return boolean
     */
    public boolean isPit() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Pit")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is an EnergySpace.
     * @return boolean
     */
    public boolean isEnergySpace() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("EnergySpace")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a ControlPoint.
     * @return boolean
     */
    public int isControlPoint() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("ControlPoint")) {

                ControlPointFieldObject controlPointFieldObject = (ControlPointFieldObject) fieldObject;

                return controlPointFieldObject.getCount();

            }

        }

        return 0;

    }
    /**
     * This method checkss if fieldObject is an Antenna.
     * @return boolean
     */
    public boolean isAntenna() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Antenna")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is an RestartPoint.
     * @return boolean
     */
    public boolean isRestartPoint() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RestartPoint")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method returns the Orientation of the RestartPoint
     */
    public String getRestartOrientation() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RestartPoint")) {

                RestartPointFieldObject restartPointFieldObject = (RestartPointFieldObject) fieldObject;

                return restartPointFieldObject.getOrientation();

            }

        }

        return "";

    }
    /**
     * This method checkss if fieldObject is an ConveyorBelt.
     * @return boolean
     */
    public boolean isBlueConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Belt")) {

                BeltFieldObject beltFieldObject = (BeltFieldObject) fieldObject;

                if (beltFieldObject.getSpeed() == 2) {

                    return true;

                }

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a blue rotating ConveyorBelt.
     * @return boolean
     */
    public boolean isBlueRotatingConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RotatingBelt")) {

                RotatingBeltFieldObject beltFieldObject = (RotatingBeltFieldObject) fieldObject;

                if (beltFieldObject.getSpeed() == 2) {

                    return true;

                }

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a green ConveyorBelt.
     * @return boolean
     */
    public boolean isGreenConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Belt")) {

                BeltFieldObject beltFieldObject = (BeltFieldObject) fieldObject;

                if (beltFieldObject.getSpeed() == 1) {

                    return true;

                }

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a green rotating ConveyorBelt.
     * @return boolean
     */
    public boolean isGreenRotatingConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RotatingBelt")) {

                RotatingBeltFieldObject beltFieldObject = (RotatingBeltFieldObject) fieldObject;

                if (beltFieldObject.getSpeed() == 1) {

                    return true;

                }

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a PushPanel.
     * @return boolean
     */
    public boolean isPushPanel() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("PushPanel")) {

                return true;

            }

        }

        return false;

    }

    /**
     * This method checkss if fieldObject is a Gear.
     * @return boolean
     */
    public boolean isGear() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Gear")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a Laser.
     * @return boolean
     */
    public boolean isLaser() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Laser")) {

                return true;

            }

        }

        return false;

    }
    /**
     * This method checkss if fieldObject is a StartingPoint.
     * @return boolean
     */
    public boolean isStartingPoint() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("StartPoint")) {

                return true;

            }

        }

        return false;

    }

    /**
     * This method returns the lasers orientation.
     */
    public String getLaserOrientation() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Laser")) {

                LaserFieldObject laserFieldObject = (LaserFieldObject) fieldObject;

                return laserFieldObject.getOrientation();

            }

        }

        return "";

    }
    /**
     * This method returns the register of push panel fieldObjects
     */
    public int[] getPushPanelRegister() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("PushPanel")) {

                PushPanelFieldObject pushPanelFieldObject = (PushPanelFieldObject) fieldObject;

                return pushPanelFieldObject.getRegisters();

            }

        }

        return null;

    }
    /**
     * This method checkss if fieldObject is a wall.
     * @return boolean
     */
    public boolean isWall() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Wall")) {

                return true;

            }

        }

        return false;

    }

    @Override

    /**
     * This method returns all types of gameboard fieldObjects converted to Strings.
     * @return returnValue.toString()
     */
    public String toString() {

        StringBuilder returnValue = new StringBuilder();

        for (GameBoardFieldObject gameBoardFieldObject : field) {

            returnValue.append(gameBoardFieldObject.getType());

        }

        return returnValue.toString();

    }

    /**
     * This method returns all fieldObjects with ConveyorBelts.
     * @return conveyorBeltFieldObject
     */
    public BeltFieldObject getConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Belt")) {

                return  (BeltFieldObject) fieldObject;

            }

        }

        return null;

    }
    /**
     * This method returns all fieldObjects with rotating ConveyorBelts.
     * @return  rotating conveyorBeltFieldObject
     */
    public RotatingBeltFieldObject getRotatingConveyorBelt() {

        for (GameBoardFieldObject gameBoardFieldObject : field) {

            if (gameBoardFieldObject.getType().equals("RotatingBelt")) {

                return (RotatingBeltFieldObject) gameBoardFieldObject;

            }


        }

        return null;

    }

    /**
     * This method returns all fieldObjects with lasers.
     * @return laserFieldObjects
     */
    public LaserFieldObject getLaser() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Laser")) {

                return (LaserFieldObject) fieldObject;

            }

        }

        return null;

    }
    /**
     * This method returns all fieldObjects with walls.
     * @return WallFieldObject
     */
    public WallFieldObject getWalls() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Wall")) {

                return (WallFieldObject) fieldObject;

            }

        }

        return null;

    }

    /**
     * This method returns all fieldObjects with gears.
     * @return GearFieldObject
     */
    public GearFieldObject getGear() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Gear")) {

                return (GearFieldObject) fieldObject;

            }

        }

        return null;

    }

    public String[] getWallOrientations() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Wall")) {

                WallFieldObject wallFieldObject = (WallFieldObject) fieldObject;

                return wallFieldObject.getOrientations();

            }

        }

        return new String[0];

    }

    /**
     * This method activates all boardElements on gameboard.
     */
    public void activate(Game game, GameState gameState, int playerID) {

        for (GameBoardFieldObject gameBoardFieldObject : field) {

            gameBoardFieldObject.activate(game, gameState, playerID);

        }

    }

}
