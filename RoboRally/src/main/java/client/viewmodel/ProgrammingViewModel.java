package client.viewmodel;

import client.network.ClientThread;
import client.view.ProgrammingButton;
import client.view.ProgrammingController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Duration;
import utilities.messages.SelectionFinished;
import javafx.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class ProgrammingViewModel {

    ClientThread clientThread;
    ProgrammingController programmingController;
    String[] cards;
    String[] cardsYouGotNow;
    private Integer seconds = 30;

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
        programmingController.createCardButtons(cards);
    }

    public ProgrammingController getProgrammingController() {
        return programmingController;
    }

    public void setTimer(){
       programmingController.initiateTimer();
    }
    public void endTimer() {
        programmingController.setTimerEnded();
    }

    public void selectCard(String cardString, int register) {
        clientThread.sendSelectedCard(cardString, register);
    }

    public void confirmRegister(int register) {
        programmingController.setRegisterActive(register);
    }

    public String[] getCardsYouGotNow() {
        return cardsYouGotNow;
    }

    public void setCardsYouGotNow(String[] yourCards) {
        cardsYouGotNow = yourCards;
    }
}
