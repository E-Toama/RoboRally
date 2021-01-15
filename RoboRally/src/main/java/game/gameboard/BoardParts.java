package game.gameboard;

import game.gameboard.boardelements.*;

/**
 * This class holds the static 2D-Arrays representing the individual board parts of the game board.
 * The parts are named according to the RoboRally-Rulebook and are used to create the race courses.
 */
public class BoardParts {

    public static final BoardElement[][] StartBoard = {
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Belt("right", 1)})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Wall(new String[]{"up", "left"}), new Laser("down", 3)}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new Antenna()}),
                    new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Wall(new String[]{"right"})})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Wall(new String[]{"right"})})
            },
            {       new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Wall(new String[]{"down"})}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new StartPoint()}),
                    new BoardElement(new FieldElement[]{new Empty()})
            },
            {       new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Empty()}),
                    new BoardElement(new FieldElement[]{new Belt("right", 1)})
            }
    };

        public static final BoardElement[][] _5B = {
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new EnergySpace(1)})
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"down", "left"})}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"left", "right"})}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"left", "left"})}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)}),
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new EnergySpace(1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"up", "right"})}),
                        new BoardElement(new FieldElement[]{new Belt("left", 2)})
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"up"}), new Laser("down", 1)}),
                        new BoardElement(new FieldElement[]{new RestartPoint("down")}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"left"}), new Laser("right", 1)}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"right"}), new Laser("left", 1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new ControlPoint()}),
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"down"}), new Laser("up", 1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new EnergySpace(1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()})
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new EnergySpace(1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"up"}), new Laser("down", 1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()})
                },
                {       new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("down", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"left"}), new Laser("right", 1)}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"right"}), new Laser("left", 1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Wall(new String[]{"down"}), new Laser("up", 1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()})
                },
                {       new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"down", "right"})}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new EnergySpace(1)}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()})
                },
                {       new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"right", "left"})}),
                        new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("right", 2)}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"right", "right"})}),
                        new BoardElement(new FieldElement[]{new RotatingBelt(2, true, new String[]{"up", "left"})}),
                        new BoardElement(new FieldElement[]{new Empty()})
                },
                {       new BoardElement(new FieldElement[]{new EnergySpace(1)}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Empty()}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Belt("up", 2)}),
                        new BoardElement(new FieldElement[]{new Empty()})
                }
        };

}
