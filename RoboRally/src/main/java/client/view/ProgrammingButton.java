package client.view;


import client.utilities.ImageBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProgrammingButton extends Button {
    private String cardString;
    private boolean chosen;
    private final int number;
    private Label label;
    private int register;

    public ProgrammingButton(int number, String cardString) {
        this.register = -1;
        this.number = number;
        this.cardString = cardString;
        this.setGraphic(ImageBuilder.createCardImage(cardString));
        chosen = false;
    }

    public boolean isChosen() {
        return chosen;
    }

    public int getNumber() {
        return number;
    }

    public int getRegister() {
        return this.register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getCardString() {
        return cardString;
    }
}
