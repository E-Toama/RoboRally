package client.viewmodel;

import client.network.ClientThread;
import client.view.MainViewController;
import game.player.Player;

import java.util.HashMap;

public class MainViewModel {

    private final ClientThread clientThread;
    private MainViewController mainViewController;

    public MainViewModel() {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(this);
        //Model <-> Controller
        mainViewController = new MainViewController();
        mainViewController.setMainViewModel(this);

    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public void switchScenes() {
        mainViewController.switchScenes();
    }

    public void updateOtherPlayers(HashMap<Integer, Player> playerList) {
        //ToDo: Get values from Playerlist and update PlayerMats
    }

    public void setCardsYouGotNow(String[] yourCards) {
        //ToDo: Should this be displayed in the ProgrammingView? Or in the PlayerMat?
    }




}
