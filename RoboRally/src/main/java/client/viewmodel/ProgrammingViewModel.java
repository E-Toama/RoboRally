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
    private Integer seconds = 30;

    StringProperty timerLabelProperty = new SimpleStringProperty();

    public String[] getCards() {
        return cards;
    }

    public StringProperty getTimerLabelProperty() {
        return timerLabelProperty;
    }


    public ProgrammingViewModel(String[] cards) {
        this.cards = cards;
        clientThread = ClientThread.getInstance();
        programmingController = new ProgrammingController(this);
    }

    public void selectionFinished() {
        clientThread.sendSelectionFinished();
    }

    //same method as initiateTimer() in ProgrammingController
    public void setTimer(){
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);

        if(time!= null){
            time.stop();
        }
    KeyFrame frame =
        new KeyFrame(
            Duration.seconds(1),
            new EventHandler<ActionEvent>() {

              @Override
              public void handle(ActionEvent event) {

                seconds--;
                timerLabelProperty.setValue("Timer started: " + seconds.toString());
                if (seconds <= 0) {
                  time.stop();
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setHeaderText("Timer run out!");
                  alert.show();
                }
              }
            });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

   /**  public void setTimer() {


        int seconds = 30;
        while (seconds > 0) {
            timerLabelProperty.setValue("Timer started: " + seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds--;
        }
        // Timer ended...
    }
    **/


    public void selectCard(String cardString, int register) {
        clientThread.sendSelectedCard(cardString, register);
    }

    public void confirmRegister(int register) {
        programmingController.setRegisterActive(register);
    }


}
