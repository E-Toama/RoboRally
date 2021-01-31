package client.viewmodel;

import client.network.ClientThread;
import client.view.MainViewController;
import game.player.Player;

import java.util.HashMap;

public class MainViewModel {

    private final ClientThread clientThread;
    private MainViewController mainViewController;

    public MainViewModel(MainViewController mainViewController) {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(this);
        //Model <-> Controller
        this.mainViewController = mainViewController;

    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public void switchScenes() {
        mainViewController.switchScenes();
    }





}
