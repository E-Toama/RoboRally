package client.view;
import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Controller class of the ingame chat
 * @author
 */
public class InGameChatController  {

    private final ChatViewModel inGameChatModel = new ChatViewModel();

  @FXML private GridPane miniChatGridPane;

  @FXML private ScrollPane miniChatListPane;

  @FXML private ListView<String> miniChatListView;

  @FXML private HBox miniChatHbox;

  @FXML private TextField miniChatTextField;

  @FXML private Button miniChatSendButton;

  @FXML private ComboBox dropDown3;


    /**
     * binds the FXML attributes to the inGameChatModel properties and initializes the dropdown menu with players
     */
   @FXML
   void initialize() {

    miniChatTextField.textProperty().bindBidirectional(inGameChatModel.chatTextProperty());
    miniChatListView.setItems(inGameChatModel.getClientThread().chatMessages);
    dropDown3.setItems(inGameChatModel.getClientThread().observablePlayerListWithDefault);
    dropDown3.getSelectionModel().selectFirst();

  }

    /**
     *  calls sendChat() in the inGameChatModel
     */
  public void sendChat() {

    inGameChatModel.sendChat();

  }

    /**
     *  calls changeDestination in the inGameChatModel with the chosen value in from the dropdown menu
     */
  public void changeDestination() {

    inGameChatModel.changeDestination(inGameChatModel.getClientThread().messageMatchMap.get(dropDown3.getValue()));

  }

}

