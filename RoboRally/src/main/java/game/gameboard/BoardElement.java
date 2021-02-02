package game.gameboard;

import game.Game;
import game.Robots.Robot;
import game.gameboard.gameboardfieldobjects.*;
import game.utilities.GameState;
import game.utilities.Position;
import game.utilities.PositionLookUp;

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

    public Position getXY() {
        return xy;
    }

    public GameBoardMapObject returnGameBoardMapObject() {
        return new GameBoardMapObject(position, field);
    }

    public GameBoardFieldObject[] getField() {
        return field;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public int getPosition() {
        return position;
    }

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

    public boolean checkIfElementCanEnteredInThisDirection(String movingDirection) {

        String oppositeDirection = getOppositeDirection(movingDirection);

        return checkIfElementCanLeftInThisDirection(oppositeDirection);

    }

    public String getOppositeDirection(String movingDirection) {

        return switch (movingDirection) {
            case "up" ->  "down";
            case "left" -> "right";
            case "down" -> "up";
            case "right" -> "left";
            default -> null;
        };

    }

    public boolean isPit() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Pit")) {

                return true;

            }

        }

        return false;

    }

    public boolean isEnergySpace() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("EnergySpace")) {

                return true;

            }

        }

        return false;

    }

    public int isControlPoint() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("ControlPoint")) {

                ControlPointFieldObject controlPointFieldObject = (ControlPointFieldObject) fieldObject;

                return controlPointFieldObject.getCount();

            }

        }

        return 0;

    }

    public boolean isAntenna() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RestartPoint")) {

                return true;

            }

        }

        return false;

    }

    public boolean isRestartPoint() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RestartPoint")) {

                return true;

            }

        }

        return false;

    }

    public String getRestartOrientation() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RestartPoint")) {

                RestartPointFieldObject restartPointFieldObject = (RestartPointFieldObject) fieldObject;

                return restartPointFieldObject.getOrientation();

            }

        }

        return "";

    }

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

    public boolean isPushPanel() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("PushPanel")) {

                return true;

            }

        }

        return false;

    }

    public boolean isGear() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Gear")) {

                return true;

            }

        }

        return false;

    }

    public boolean isLaser() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Laser")) {

                return true;

            }

        }

        return false;

    }

    public String getLaserOrientation() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Laser")) {

                LaserFieldObject laserFieldObject = (LaserFieldObject) fieldObject;

                return laserFieldObject.getOrientation();

            }

        }

        return "";

    }

    public int[] getPushPanelRegister() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("PushPanel")) {

                PushPanelFieldObject pushPanelFieldObject = (PushPanelFieldObject) fieldObject;

                return pushPanelFieldObject.getRegisters();

            }

        }

        return null;

    }

    public boolean isWall() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Wall")) {

                return true;

            }

        }

        return false;

    }

    public boolean isBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Belt")) {

                    return true;

            }

        }

        return false;

    }

    public boolean isRotatingBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RotatingBelt")) {

                    return true;

            }

        }

        return false;

    }

    public BeltFieldObject getConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("Belt")) {

                return  (BeltFieldObject) fieldObject;

            }

        }

        return null;

    }

    public RotatingBeltFieldObject getRotatingConveyorBelt() {

        for (GameBoardFieldObject fieldObject : field) {

            if (fieldObject.getType().equals("RotatingBelt")) {

                return (RotatingBeltFieldObject) fieldObject;

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

    public void activate(Game game, GameState gameState, int playerID) {

        for (GameBoardFieldObject gameBoardFieldObject : field) {

            gameBoardFieldObject.activate(game, gameState, playerID);

        }

    }

}
