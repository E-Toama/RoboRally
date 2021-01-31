package client.view;
import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class InGameChatController  {

   // private final InGameChatModel inGameChatModel = new InGameChatModel();
    private final ChatViewModel inGameChatModel = new ChatViewModel();

  @FXML private GridPane miniChatGridPane;

  @FXML private ScrollPane miniChatListPane;

  @FXML private ListView<String> miniChatListView;

  @FXML private HBox miniChatHbox;

  @FXML private TextField miniChatTextField;

  @FXML private Button miniChatSendButton;

  @FXML private ComboBox dropDown3;

   @FXML
   void initialize() {

    miniChatTextField.textProperty().bindBidirectional(inGameChatModel.chatTextProperty());
    miniChatListView.setItems(inGameChatModel.getClientThread().chatMessages);
    dropDown3.setItems(inGameChatModel.getClientThread().observablePlayerListWithDefault);
    dropDown3.getSelectionModel().selectFirst();

  }


  public void sendChat() {

    inGameChatModel.sendChat();

  }

  public void changeDestination() {

    inGameChatModel.changeDestination(inGameChatModel.getClientThread().messageMatchMap.get(dropDown3.getValue()));

  }

}

