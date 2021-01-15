package client.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController {

    private static ViewController viewController = new ViewController(new Stage());

    private Stage primaryStage;

    private ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static ViewController getViewController() {
        return  viewController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

}
