package client.view;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class InGameChatController {

    @FXML
    private GridPane miniChatGridPane;

    @FXML
    private ScrollPane miniChatListVIew;

    @FXML
    private ListView<?> miniChatListView;

    @FXML
    private HBox miniChatHbox;

    @FXML
    private TextField miniChatTextField;

    @FXML
    private Button miniChatSendButton;

}
