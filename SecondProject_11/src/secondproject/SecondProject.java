
package secondproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Burcu, Kubra
 */
public class SecondProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Enter IP address of host");
            primaryStage.setMinHeight(200);
            primaryStage.setMinWidth(400);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(SecondProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);

    }
}
