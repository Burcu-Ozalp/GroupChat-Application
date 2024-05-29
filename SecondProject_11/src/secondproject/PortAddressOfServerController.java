/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Burcu, Kubra
 */
public class PortAddressOfServerController implements Initializable {

    @FXML
    private TextField textBarOfHost;
    @FXML
    private Button OKButton;
    @FXML
    private Button cancelButton;

    static int port;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        PortAddressOfServerController.port = port;
    }

    @FXML
    private void clickOKAction(ActionEvent event) throws IOException {

        Stage stage2 = (Stage) cancelButton.getScene().getWindow();
        stage2.close();

        Stage stage = new Stage();

        port = Integer.parseInt(textBarOfHost.getText());

        Parent root = FXMLLoader.load(getClass().getResource("Running.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Server Running...");
        stage.setMinHeight(200);
        stage.setMinWidth(400);

        stage.show();
    }

    @FXML
    private void clickCancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
