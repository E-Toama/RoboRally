package game.gameboard;

import game.gameboard.boardelements.BoardElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MapElement {
    private int position;
    private ArrayList<BoardElement> field;

    public MapElement(int position, BoardElement firstElement) {
        this.position = position;
        this.field = new ArrayList<>(Collections.singletonList(firstElement));
    }

    public MapElement(int position, BoardElement firstElement, BoardElement secondElement) {
        this.position = position;
        this.field = new ArrayList<>(Arrays.asList(firstElement, secondElement));
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<BoardElement> getField() {
        return field;
    }

}
