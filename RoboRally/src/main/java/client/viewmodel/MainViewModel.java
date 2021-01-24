package client.viewmodel;

import client.network.ClientThread;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainViewModel {
    private final ClientThread clientThread;
    private ProgrammingViewModel programmingViewModel;
    private ChatViewModel chatViewModel;
    private PlayerMatModel playerMatModel;
    private GameBoardViewModel gameBoardViewModel;

    public MainViewModel() {
        this.clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(this);

    }

    public void setTimer() {
        programmingViewModel.setTimer();
    }

    public void switchSubviews() {
        //ToDo:
    }

    public void confirmRegister(int register) {
        programmingViewModel.confirmRegister(register);
    }

    public void createProgrammingView(String[] cards) {
        //ToDo: 1. create ProgrammingView
        //      2. replace PlayerMat with ProgrammingView
        //      (3. adjust ProgrammingView SIZE)
        programmingViewModel = new ProgrammingViewModel(cards);

    }

   /* public Scene createMainView(){
        GridPane mainView = FXMLLoader.load(getClass().getResource("/FXMLFiles/MainView.fxml"));

        AnchorPane playerMat = FXMLLoader.load(getClass().getResource("/FXMLFiles/PlayerMatVorschauCards.fxml"));

        GridPane boardView = new GameBoardViewModel().createGameBoardView(gameBoard.getGameBoard());
        boardView.setAlignment(Pos.TOP_CENTER);

        mainView.add(boardView, 1, 0);
        mainView.add(playerMat, 0, 1, 1, 2);
    }*/


}
