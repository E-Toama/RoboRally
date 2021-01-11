package game.gameboard;

import game.gameboard.boardelements.*;

import javax.swing.*;

public class BoardParts {

    public static final MapElement[][] Start_A = {
            {new MapElement(1, new Empty()),                new MapElement(2, new Empty()),             new MapElement(3,new Belt())},
            {new MapElement(4, new Empty()),                new MapElement(5, new StartingPosition()),  new MapElement(6, new Empty())},
            {new MapElement(7, new Empty()),                new MapElement(8, new Wall()),              new MapElement(9,new Empty())},
            {new MapElement(10, new StartingPosition()),    new MapElement(11, new Empty()),            new MapElement(12, new Empty())},
            {new MapElement(13, new Antenna()),             new MapElement(14, new StartingPosition()), new MapElement(15, new Wall())},
            {new MapElement(16, new Empty()),               new MapElement(17, new StartingPosition()), new MapElement(18, new Wall())},
            {new MapElement(19, new StartingPosition()),    new MapElement(20, new Empty()),            new MapElement(21, new Empty())},
            {new MapElement(22, new Empty()),               new MapElement(23, new Wall()),             new MapElement(24,new Empty())},
            {new MapElement(25, new Empty()),               new MapElement(26, new StartingPosition()), new MapElement(27, new Empty())},
            {new MapElement(28, new Empty()),               new MapElement(29, new Empty()),            new MapElement(30,new Belt())}
    };

    public static final MapElement[][] _5B = {
            {       new MapElement(1, new Empty()),
                    new MapElement(2, new Belt()),
                    new MapElement(3, new Belt()),
                    new MapElement(4, new Empty()),
                    new MapElement(5, new Empty()),
                    new MapElement(6, new Empty()),
                    new MapElement(7, new Empty()),
                    new MapElement(8, new Empty()),
                    new MapElement(9, new Empty()),
                    new MapElement(10, new EnergySpace())
            },
            {       new MapElement(11,new Empty()),
                    new MapElement(12,new Belt()),
                    new MapElement(13, new Belt()),
                    new MapElement(14, new Belt()),
                    new MapElement(15, new Belt()),
                    new MapElement(16, new Belt()),
                    new MapElement(17, new Belt()),
                    new MapElement(18, new Belt()),
                    new MapElement(19, new Belt()),
                    new MapElement(20, new Belt())
            },
            {       new MapElement(21,new Empty()),
                    new MapElement(22, new Belt()),
                    new MapElement(23, new EnergySpace()),
                    new MapElement(24,new Empty()),
                    new MapElement(25,new Empty()),
                    new MapElement(26,new Empty()),
                    new MapElement(27,new Empty()),
                    new MapElement(28,new Empty()),
                    new MapElement(29, new Belt()),
                    new MapElement(30, new Belt())
            },
            {       new MapElement(31,new Empty()),
                    new MapElement(32, new Belt()),
                    new MapElement(33,new Empty()),
                    new MapElement(34, new Laser(), new Wall()),
                    new MapElement(35, new Reboot()),
                    new MapElement(36, new Laser(), new Wall()),
                    new MapElement(37, new Laser(), new Wall()),
                    new MapElement(38,new Empty()),
                    new MapElement(39, new Belt()),
                    new MapElement(40, new ControlPoint())
            },
            {       new MapElement(41,new Empty()),
                    new MapElement(42, new Belt()),
                    new MapElement(43,new Empty()),
                    new MapElement(44, new Laser(), new Wall()),
                    new MapElement(45,new Empty()),
                    new MapElement(46, new EnergySpace()),
                    new MapElement(47,new Empty()),
                    new MapElement(48,new Empty()),
                    new MapElement(49, new Belt()),
                    new MapElement(50,new Empty()),
            },
            {       new MapElement(51,new Empty()),
                    new MapElement(52, new Belt()),
                    new MapElement(53,new Empty()),
                    new MapElement(54,new Empty()),
                    new MapElement(55, new EnergySpace()),
                    new MapElement(56,new Empty()),
                    new MapElement(57, new Laser(), new Wall()),
                    new MapElement(58,new Empty()),
                    new MapElement(59, new Belt()),
                    new MapElement(60,new Empty())
            },
            {       new MapElement(61,new Empty()),
                    new MapElement(62, new Belt()),
                    new MapElement(63,new Empty()),
                    new MapElement(64, new Laser(), new Wall()),
                    new MapElement(65, new Laser(), new Wall()),
                    new MapElement(66,new Empty()),
                    new MapElement(67, new Laser(), new Wall()),
                    new MapElement(68,new Empty()),
                    new MapElement(69, new Belt()),
                    new MapElement(70,new Empty())
            },
            {       new MapElement(71, new Belt()),
                    new MapElement(72, new Belt()),
                    new MapElement(73,new Empty()),
                    new MapElement(74,new Empty()),
                    new MapElement(75,new Empty()),
                    new MapElement(76,new Empty()),
                    new MapElement(77,new Empty()),
                    new MapElement(78, new EnergySpace()),
                    new MapElement(79, new Belt()),
                    new MapElement(80,new Empty())
            },
            {       new MapElement(81,new Belt()),
                    new MapElement(82, new Belt()),
                    new MapElement(83, new Belt()),
                    new MapElement(84, new Belt()),
                    new MapElement(85, new Belt()),
                    new MapElement(86, new Belt()),
                    new MapElement(87, new Belt()),
                    new MapElement(88, new Belt()),
                    new MapElement(89, new Belt()),
                    new MapElement(90,new Empty())
            },
            {       new MapElement(91, new EnergySpace()),
                    new MapElement(92, new Empty()),
                    new MapElement(93, new Empty()),
                    new MapElement(94, new Empty()),
                    new MapElement(95, new Empty()),
                    new MapElement(96, new Empty()),
                    new MapElement(97, new Empty()),
                    new MapElement(98, new Belt()),
                    new MapElement(99, new Belt()),
                    new MapElement(100,new Empty())
            }
    };

}
