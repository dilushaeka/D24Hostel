import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("start");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        primaryStage.setScene(new Scene(load));
        primaryStage.show();
        //FactoryConfiguration.getInstance();
    }
}
