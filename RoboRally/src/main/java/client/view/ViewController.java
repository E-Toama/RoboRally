package client.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the primary stage
 * @author
 */
public class ViewController {

    private static ViewController viewController = new ViewController(new Stage());

    private Stage primaryStage;

    /**
     * constructor of ViewController with primaryStage
     * @param primaryStage
     */
    private ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * @return ViewController viewController
     */
    public static ViewController getViewController() {
        return  viewController;
    }

    /**
     * sets primaryStage
     * @param primaryStage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * sets the scene of the primaryStage
     * @param scene
     */
    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * sets the title of the primaryStage
     * @param title
     */
    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

}
