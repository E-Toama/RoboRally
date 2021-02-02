package client.view;

import client.utilities.ImageBuilder;
import client.viewmodel.ProgrammingViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ProgrammingController {

  private final Integer startTime = 30;
  ProgrammingViewModel programmingViewModel;
  GridPane gridPane;
  ProgrammingButton[] buttonList = new ProgrammingButton[9];
  VBox timerBox;
  Label timerHeading;
  Label timerText;
  Label timerLabel;
  private Integer seconds = startTime;
  private boolean[] filledRegisters;
  private boolean isTimerEnded = false;
  private String slowPlayers;

  public ProgrammingController() {
    filledRegisters = new boolean[5];

    // Build ProgrammingPane
    gridPane = new GridPane();

    // Build Timer display
    timerBox = new VBox();
    timerBox.setVisible(false);
    timerHeading = new Label("Timer");
    timerText = new Label("running:");
    timerLabel = new Label("");
    timerBox.getChildren().addAll(timerHeading, timerText, timerLabel);

    //ID's for stylesheet.css
    gridPane.getStylesheets().add("stylesheet.css");
    gridPane.setId("programmingGridPane");
    timerBox.setId("timerbox");
    timerLabel.setId("timerlabel");
    timerHeading.setId("timerheading");

    gridPane.addColumn(9, timerBox);
  }

  public void initialize() {
    timerLabel.textProperty().bindBidirectional(programmingViewModel.getTimerLabelProperty());
  }

  // Getters and Setters

  public GridPane getGridPane() {
    return gridPane;
  }

  public void setProgrammingModel(ProgrammingViewModel programmingViewModel) {
    this.programmingViewModel = programmingViewModel;
  }

  public void setSlowPlayers(String slowPlayers) {
    this.slowPlayers = slowPlayers;
  }

  public void createCards() {
    createCardButtons(programmingViewModel.getCards());
  }

  public void createCardButtons(String[] cards) {

    for (int i = 0; i < 9; i++) {
      ProgrammingButton cardButton = new ProgrammingButton(i, cards[i]);
      Label label = new Label();
      label.setId(String.valueOf(i));
      label.setPrefWidth(91);
      label.setAlignment(Pos.CENTER);
      cardButton.setOnAction(e -> selectCard(cardButton));
      cardButton.setLabel(label);
      gridPane.add(cardButton, i, 0);
      gridPane.add(cardButton.getLabel(), i, 1);
      cardButton.setId("cardButton");
      cardButton.getLabel().setId("cardButtonLabel");

      // Add Buttons and labels to way too many lists
      buttonList[i] = cardButton;
    }
  }

  private void selectCard(ProgrammingButton button) {
    if (button.isChosen()) {
      button.setChosen(false);
      int concerningRegister = button.getRegister();
      filledRegisters[concerningRegister] = false;
      programmingViewModel.selectCard("null", (concerningRegister + 1));
    } else {
      int firstFreeRegisterIndex = findFirstFreeRegisterIndex();

      // Again-Card cannot be placed in first register
      if (firstFreeRegisterIndex == 0 && "Again".equals(button.getCardString())) {
        return;
      }
      button.setChosen(true);
      filledRegisters[firstFreeRegisterIndex] = true;
      button.setRegister(firstFreeRegisterIndex);
      programmingViewModel.selectCard(button.getCardString(), firstFreeRegisterIndex + 1);
    }
  }

  private int findFirstFreeRegisterIndex() {
    int firstFreeRegister = 0;
    for (int i = 0; i < filledRegisters.length; i++) {
      if (!filledRegisters[i]) {
        firstFreeRegister = i;
        break;
      }
    }
    return firstFreeRegister;
  }

  public void setRegisterActive(int register) {
    for (ProgrammingButton button : buttonList) {
      if (register == button.getRegister() + 1) {
        if (!button.isChosen()) {
          button.setRegister(-1);
          button.setStyle("-fx-background-color: #CED0CE");
          button.getLabel().setText("");
        } else {
          button.setStyle("-fx-background-color: PURPLE");
          button.getLabel().setText("Register: " + (button.getRegister() + 1));
          allRegistersChosen();
        }
      }
    }
  }

  public void discardHand() {
    for (ProgrammingButton btn : buttonList) {

      if (!btn.isChosen()) btn.setDisable(true);
      btn.setStyle("-fx-background-color: #3e0202");
      btn.setGraphic(ImageBuilder.adjustToProgrammingView("default"));
    }
  }

  private void allRegistersChosen() {
    boolean allChosen = true;
    for (boolean b : filledRegisters) {
      allChosen = allChosen && b;
    }
    if (allChosen) {
      for (ProgrammingButton button : buttonList) {
        button.setDisable(true);
      }
    }
  }

  public void setTimerEnded() {
    isTimerEnded = true;
  }

  /** Starts timer of 30 seconds in programmingView */
  public void initiateTimer() {
    timerBox.setVisible(true);

    Timeline time = new Timeline();
    time.setCycleCount(Timeline.INDEFINITE);
    if (time != null) {
      time.stop();
    }
    KeyFrame frame =
        new KeyFrame(
            Duration.seconds(1),
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {

                seconds--;
                timerLabel.setText(seconds.toString());

                if (seconds <= 0) {
                  time.stop();
                  timerLabel.setText("Over!");
                }
              }
            });
    time.getKeyFrames().add(frame);
    time.playFromStart();
  }
}
