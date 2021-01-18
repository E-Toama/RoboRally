package game.gameboardV2.boards;


import game.gameboardV2.BoardElement;
import game.gameboardV2.gameboardfieldobjects.BeltFieldObject;
import game.gameboardV2.gameboardfieldobjects.ControlPointFieldObject;
import game.gameboardV2.gameboardfieldobjects.EmptyFieldObject;
import game.gameboardV2.gameboardfieldobjects.EnergySpaceFieldObject;
import game.gameboardV2.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboardV2.gameboardfieldobjects.LaserFieldObject;
import game.gameboardV2.gameboardfieldobjects.RestartPointFieldObject;
import game.gameboardV2.gameboardfieldobjects.RotatingBeltFieldObject;
import game.gameboardV2.gameboardfieldobjects.WallFieldObject;

public class DizzyHighway {

    public static final BoardElement[][] _5B = {
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"down", "left"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"left", "right"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"left", "left"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"up", "right"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"up"}), new LaserFieldObject("down", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RestartPointFieldObject("down")}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"left"}), new LaserFieldObject("right", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"}), new LaserFieldObject("left", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new ControlPointFieldObject(1)}),
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"}), new LaserFieldObject("up", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"up"}), new LaserFieldObject("down", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"left"}), new LaserFieldObject("right", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"}), new LaserFieldObject("left", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"}), new LaserFieldObject("up", 1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"down", "right"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"right", "left"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"right", "right"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"up", "left"})}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(30, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(30, new GameBoardFieldObject[]{new EmptyFieldObject()})
            }
    };
}
