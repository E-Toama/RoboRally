package client.view;

import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.viewmodel.ProgrammingViewModel;
import game.robots.Robot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Controller class of the programming mat
 * 
 */
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

  /**
   * constructor for ProgrammingController
   * initializes filled registers and builds timer
   */
  public ProgrammingController() {
    filledRegisters = new boolean[5];

    // Build ProgrammingPane
    gridPane = new GridPane();

    timerBox = new VBox();
    timerBox.setVisible(false);
    timerHeading = new Label("Timer");
    timerText = new Label("running:");
    timerLabel = new Label("");
    timerBox.getChildren().addAll(timerHeading, timerText, timerLabel);

    //ID's for stylesheet.css
    gridPane.getStylesheets().add("FXMLFiles/stylesheet.css");
    gridPane.setId("programmingGridPane");
    timerBox.setId("timerbox");
    timerLabel.setId("timerlabel");
    timerHeading.setId("timerheading");

    gridPane.addColumn(9, timerBox);
  }

  /**
   * bindz timerLabel to the programmingViewModel property
   */
  public void initialize() {
    timerLabel.textProperty().bindBidirectional(programmingViewModel.getTimerLabelProperty());
  }


  /**
   * @return GridPane gridPane of the programming mat
   */
  public GridPane getGridPane() {
    return gridPane;
  }

  /**
   * sets programming viewmodel
   * @param programmingViewModel
   */
  public void setProgrammingModel(ProgrammingViewModel programmingViewModel) {
    this.programmingViewModel = programmingViewModel;
  }

  public void setSlowPlayers(String slowPlayers) {
    this.slowPlayers = slowPlayers;
  }

  /**
   * creates cards buttons with  programming cards
   */
  public void createCards() {
    createCardButtons(programmingViewModel.getCards());
  }

  /**
   * creates nine buttons with one label each for the programming cards
   * @param cards
   */
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
      cardButton.getLabel().getStyleClass().add("cartButtonLabel");

      // Add Buttons and labels to way too many lists
      buttonList[i] = cardButton;
    }
  }

  /**
   * puts a programming card into a register
   * @param button
   */
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
        button.getLabel().setText("Not in Reg. 1!");
        return;
      }
      button.setChosen(true);
      filledRegisters[firstFreeRegisterIndex] = true;
      button.setRegister(firstFreeRegisterIndex);
      programmingViewModel.selectCard(button.getCardString(), firstFreeRegisterIndex + 1);
    }
  }

  /**
   * responsible for finding the first available register
   * @return the first free register index
   */
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

  /**
   * sets the programming cards buttons in a register as active
   * @param register the chosen register
   */
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

  /**
   * discards the players cards
   */
  public void discardHand() {
    for (ProgrammingButton btn : buttonList) {

      if (!btn.isChosen()) btn.setDisable(true);
      btn.setStyle("-fx-background-color: #3e0202");
      btn.setGraphic(ImageBuilder.adjustToProgrammingView("default"));
    }
  }

  /**
   * checks if all five registers are filled with programming cards
   */
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

  /**
   * sets isTimerEnded true
   */
  public void setTimerEnded() {
    isTimerEnded = true;
  }

  /**
   * Starts a timer of 30 seconds in programmingView
   */
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

  public void setPlayerValues(String userName, int robotFigure) {

    Label name = new Label(userName);
    name.setId("programmingName");

    Text bot = new Text(Robot.getRobotName(robotFigure));
    bot.setFill(RobotImageBuilder.getRobotColor(robotFigure));
    bot.setId("programmingBot");

    gridPane.add(name, 0, 2, 3, 1);
    gridPane.add(bot, 8, 2, 2, 1);
  }
}
