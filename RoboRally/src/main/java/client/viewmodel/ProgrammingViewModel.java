package client.viewmodel;

import client.network.ClientThread;
import client.view.ProgrammingButton;
import client.view.ProgrammingController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import utilities.messages.SelectionFinished;

public class ProgrammingViewModel {

    ClientThread clientThread;
    ProgrammingController programmingController;
    String[] cards;

    StringProperty timerLabelProperty = new SimpleStringProperty();

    public String[] getCards() {
        return cards;
    }

    public StringProperty getTimerLabelProperty() {
        return timerLabelProperty;
    }


    public ProgrammingViewModel() {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setProgrammingViewModel(this);
        //Model <-> Controller
        programmingController = new ProgrammingController();
        programmingController.setProgrammingModel(this);

    }

    public void setCards(String[] cards) {
        this.cards = cards;
    }

    public void setProgrammingController(ProgrammingController programmingController) {
        this.programmingController = programmingController;
    }

    public void selectionFinished() {
        clientThread.sendSelectionFinished();
    }

    public void setTimer() {

        int seconds = 30;
        while (seconds > 0) {
            timerLabelProperty.setValue("Timer started: " + seconds);
            seconds--;
        }
        // Timer ended...
    }


    public void selectCard(String cardString, int register) {
        clientThread.sendSelectedCard(cardString, register);
    }

    public void confirmRegister(int register) {
        programmingController.setRegisterActive(register);
    }


}
