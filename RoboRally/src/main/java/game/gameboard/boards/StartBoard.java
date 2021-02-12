package game.gameboard.boards;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.*;

/**
 * This class represents the starting board for the different types of courses of the game.
 * 
 */
public class StartBoard {

    public BoardElement[][] startBoard = {
            {
                    new BoardElement(0, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(1, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(2, new GameBoardFieldObject[]{new BeltFieldObject("right", 1)})
            },
            {
                    new BoardElement(13, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(14, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(15, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(26, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(27, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"up"})}),
                    new BoardElement(28, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(39, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(40, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(41, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(52, new GameBoardFieldObject[]{new AntennaFieldObject()}),
                    new BoardElement(53, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(54, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"})})
            },
            {
                    new BoardElement(65, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(66, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(67, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"})})
            },
            {
                    new BoardElement(78, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(79, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(80, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(91, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(92, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"})}),
                    new BoardElement(93, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(104, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(105, new GameBoardFieldObject[]{new StartPointFieldObject()}),
                    new BoardElement(106, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {
                    new BoardElement(117, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(118, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(119, new GameBoardFieldObject[]{new BeltFieldObject("right", 1)})
            }
    };

}
