import client.view.ProgrammingController;
import client.viewmodel.ProgrammingViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProgrammingViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        String[] cardArray = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};
        ProgrammingViewModel programmingViewModel = new ProgrammingViewModel(cardArray);
        ProgrammingController programmingController = new ProgrammingController(programmingViewModel);
        GridPane pane = programmingController.getGridPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
