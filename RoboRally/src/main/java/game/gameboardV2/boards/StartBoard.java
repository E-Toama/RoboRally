package game.gameboardV2.boards;

import game.gameboardV2.BoardElement;
import game.gameboardV2.gameboardfieldobjects.*;

public class StartBoard {

    public static BoardElement[][] startBoard = {
            {
                    new BoardElement(0, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(1, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(2, new GameBoardFieldObject[]{new BeltFieldObject("right", 1)})
            },
            {
                    new BoardElement(3, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(4, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(5, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(6, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(7, new GameBoardFieldObject[]{new LaserFieldObject("down", 3), new WallFieldObject(new String[]{"up", "left"})}),
                    new BoardElement(8, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(9, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(10, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(11, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(12, new GameBoardFieldObject[]{new AntennaFieldObject()}),
                    new BoardElement(13, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(14, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"})})
            },
            {
                    new BoardElement(15, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(16, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(17, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"})})
            },
            {
                    new BoardElement(18, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(19, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(20, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(21, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(22, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"})}),
                    new BoardElement(23, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(24, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(25, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(26, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(27, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(28, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(29, new GameBoardFieldObject[]{new BeltFieldObject("right", 1)})
            }
    };

}
