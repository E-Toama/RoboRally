package client.viewmodel;

import client.network.ClientThread;
import client.view.MainViewController;
import game.player.Player;

import java.util.HashMap;

/**
 * ViewModel of the complete game window /mainView
 * 
 */
public class MainViewModel {

    private final ClientThread clientThread;
    private MainViewController mainViewController;

    /**
     * Constructor of the mainViewModel with client thread
     * @param mainViewController the controller class of the mainView
     */
    public MainViewModel(MainViewController mainViewController) {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(this);
        //Model <-> Controller
        this.mainViewController = mainViewController;

    }

    /**
     * gets the mainViewController
     * @return mainViewController
     */
    public MainViewController getMainViewController() {
        return mainViewController;
    }

    /**
     * calls switchScenes() in the mainViewController
     */
    public void switchScenes() {
        mainViewController.switchScenes();
    }



}
