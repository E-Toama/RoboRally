package game.gameboard;

import game.gameboard.boardelements.*;

import javax.swing.*;

public class BoardParts {

    private final BoardElement[][] Start_A = {
            {new Empty(),           new Empty(),            new Belt()},
            {new Empty(),           new StartingPosition(), new Empty()},
            {new Empty(),           new Wall(),             new Empty()},
            {new StartingPosition(),new Empty(),            new Empty()},
            {new Antenna(),         new StartingPosition(), new Wall()},
            {new Empty(),           new StartingPosition(), new Wall()},
            {new StartingPosition(),new Empty(),            new Empty()},
            {new Empty(),           new Wall(),             new Empty()},
            {new Empty(),           new StartingPosition(), new Empty()},
            {new Empty(),           new Empty(),            new Belt()}
    };

    private final BoardElement[][] _5B = {
            {new Empty(), new Belt(), new Belt(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new EnergySpace()},
            {new Empty(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt()},
            {new Empty(), new Belt(), new EnergySpace(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Belt(), new Belt()},
            {new Empty(), new Belt(), new Empty(), new Laser(), new Reboot(), new Laser(), new Laser(), new Empty(), new Belt(), new ControlPoint()},
            {new Empty(), new Belt(), new Empty(), new Laser(), new Empty(), new EnergySpace(), new Empty(), new Empty(), new Belt(), new Empty()},
            {new Empty(), new Belt(), new Empty(), new Empty(), new EnergySpace(), new Empty(), new Laser(), new Empty(), new Belt(), new Empty()},
            {new Empty(), new Belt(), new Empty(), new Laser(), new Laser(), new Empty(), new Laser(), new Empty(), new Belt(), new Empty()},
            {new Belt(), new Belt(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new EnergySpace(), new Belt(), new Empty()},
            {new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Belt(), new Empty()},
            {new EnergySpace(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Belt(), new Belt(), new Empty()}
    };

}
