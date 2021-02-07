package client.view;


import client.utilities.ImageBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *  Controller class for the programming card buttons
 * @author
 */
public class ProgrammingButton extends Button {
    private String cardString;
    private boolean chosen;
    private final int number;
    private Label label;
    private int register;

    /**
     * sets buttons with programming cards
     * @param number
     * @param cardString
     */
    public ProgrammingButton(int number, String cardString) {
        this.register = -1;
        this.number = number;
        this.cardString = cardString;
        this.setGraphic(ImageBuilder.adjustToProgrammingView(cardString));
        chosen = false;
    }


    /**
     *
     * @return boolean chosen is true if card is chosen
     */
    public boolean isChosen() {
        return chosen;
    }

    /**
     *
     * @return int number
     */
    public int getNumber() {
        return number;
    }

    /**
     * gets a register
     * @return int register
     */
    public int getRegister() {
        return this.register;
    }

    /**
     * sets a register
     * @param register
     */
    public void setRegister(int register) {
        this.register = register;
    }

    /**
     * sets if a card is chosen or not
     * @param chosen
     */
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    /**
     * gets programming button label
     * @return Label lable
     */
    public Label getLabel() {
        return label;
    }

    /**
     * sets programming button labes
     * @param label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * gets card name
     * @return String cardString
     */
    public String getCardString() {
        return cardString;
    }
}
