package game.gameboard.boards;


import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.ControlPointFieldObject;
import game.gameboard.gameboardfieldobjects.EmptyFieldObject;
import game.gameboard.gameboardfieldobjects.EnergySpaceFieldObject;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboard.gameboardfieldobjects.LaserFieldObject;
import game.gameboard.gameboardfieldobjects.RestartPointFieldObject;
import game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject;
import game.gameboard.gameboardfieldobjects.WallFieldObject;

/**
 * This class represents the Dizzy Highway course map without its starting board.
 * 
 * @author 
 */
public class DizzyHighway {

    public final BoardElement[][] _5B = {
            {       new BoardElement(3, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(4, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(5, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(6, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(7, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(8, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(9, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(10, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(11, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(12, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)})
            },
            {       new BoardElement(16, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(17, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"left", "down"})}),
                    new BoardElement(18, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"down", "left"})}),
                    new BoardElement(19, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(20, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(21, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(22, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(23, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
                    new BoardElement(24, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"up", "left"})}),
                    new BoardElement(25, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)}),
            },
            {       new BoardElement(29, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(30, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(31, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(32, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(33, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(34, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(35, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(36, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(37, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"left", "up"})}),
                    new BoardElement(38, new GameBoardFieldObject[]{new BeltFieldObject("left", 2)})
            },
            {       new BoardElement(42, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(43, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(44, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(45, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"up"})}),
                    new BoardElement(46, new GameBoardFieldObject[]{new RestartPointFieldObject("down")}),
                    new BoardElement(47, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"left"}), new LaserFieldObject("right", 1)}),
                    new BoardElement(48, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"})}),
                    new BoardElement(49, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(50, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(51, new GameBoardFieldObject[]{new ControlPointFieldObject(1)}),
            },
            {       new BoardElement(55, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(56, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(57, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(58, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"}), new LaserFieldObject("up", 1)}),
                    new BoardElement(59, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(60, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(61, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(62, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(63, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(64, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(68, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(69, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(70, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(71, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(72, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(73, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(74, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"up"}), new LaserFieldObject("down", 1)}),
                    new BoardElement(75, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(76, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(77, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(81, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(82, new GameBoardFieldObject[]{new BeltFieldObject("down", 2)}),
                    new BoardElement(83, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(84, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"left"})}),
                    new BoardElement(85, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"right"}), new LaserFieldObject("left", 1)}),
                    new BoardElement(86, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(87, new GameBoardFieldObject[]{new WallFieldObject(new String[]{"down"})}),
                    new BoardElement(88, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(89, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(90, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(94, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(95, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"right", "down"})}),
                    new BoardElement(96, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(97, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(98, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(99, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(100, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(101, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(102, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(103, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(107, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(108, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"down", "right"})}),
                    new BoardElement(109, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(110, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(111, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(112, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(113, new GameBoardFieldObject[]{new BeltFieldObject("right", 2)}),
                    new BoardElement(114, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"up", "right"})}),
                    new BoardElement(115, new GameBoardFieldObject[]{new RotatingBeltFieldObject(2, true, new String[]{"right", "up"})}),
                    new BoardElement(116, new GameBoardFieldObject[]{new EmptyFieldObject()})
            },
            {       new BoardElement(120, new GameBoardFieldObject[]{new EnergySpaceFieldObject(1)}),
                    new BoardElement(121, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(122, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(123, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(124, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(125, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(126, new GameBoardFieldObject[]{new EmptyFieldObject()}),
                    new BoardElement(127, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(128, new GameBoardFieldObject[]{new BeltFieldObject("up", 2)}),
                    new BoardElement(129, new GameBoardFieldObject[]{new EmptyFieldObject()})
            }
    };
}
