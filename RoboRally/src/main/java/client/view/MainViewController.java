package client.view;

import client.viewmodel.ChatViewModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MainViewController {

    private MainViewModel mainViewModel = new MainViewModel();
    private ProgrammingViewModel programmingViewModel;
    private GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
    private ChatViewModel chatViewModel = new ChatViewModel();
    private PlayerMatModel playerMatModel = new PlayerMatModel();

    private GameBoardView gameBoardView;
    private PlayerMatView playerMatView;

    @FXML
    GridPane mainViewPane;

   public void initialize() {
       //mainViewModel.createMainView();
   }




}
