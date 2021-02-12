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

/**
 * ViewModel class for the programming mat
 * 
 */
public class ProgrammingViewModel {

    ClientThread clientThread;
    ProgrammingController programmingController;
    String[] cards;
    String slowPlayers = "";
    String[] cardsYouGotNow = new String[5];

    private Integer seconds = 30;

    StringProperty timerLabelProperty = new SimpleStringProperty();

    public String[] getCards() {
        return cards;
    }

    public StringProperty getTimerLabelProperty() {
        return timerLabelProperty;
    }

    public void setSlowPlayers(String slowPlayers) {
        this.slowPlayers = slowPlayers;
    }

    public String getSlowPlayers() {
        return slowPlayers;
    }

    /**
     * constructor of ProgrammingViewModel with client thread and programmingController
     */
    public ProgrammingViewModel() {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setProgrammingViewModel(this);
        //Model <-> Controller
        programmingController = new ProgrammingController();
        programmingController.setProgrammingModel(this);
    }

    /**
     * sets programming cards
     * @param cards of the programming mat
     */
    public void setCards(String[] cards) {
        this.cards = cards;
        programmingController.createCardButtons(cards);
    }

    /**
     * gets programmingController
     * @return ProgrammingController programmingController
     */
    public ProgrammingController getProgrammingController() {
        return programmingController;
    }

    /**
     * sets timer
     */
    public void setTimer(){
       programmingController.initiateTimer();
    }

    /**
     * ends timer
     */
    public void endTimer() {
        programmingController.setTimerEnded();
    }

    /**
     *
     * @param cardString is the chosen card
     * @param register is the chosen register
     */
    public void selectCard(String cardString, int register) {
        cardsYouGotNow[register-1] = cardString;
        clientThread.sendSelectedCard(cardString, register);
    }

    /**
     * confirms register
     * @param register
     */
    public void confirmRegister(int register) {
        programmingController.setRegisterActive(register);
    }

    /**
     * gets programming cards of a player
     * @return
     */
    public String[] getCardsYouGotNow() {
        return cardsYouGotNow;
    }

    /**
     * sets programming cards for a player
     * @param yourCards are cards the player gets
     */
    public void setCardsYouGotNow(String[] yourCards) {
        cardsYouGotNow = yourCards;
    }
}
