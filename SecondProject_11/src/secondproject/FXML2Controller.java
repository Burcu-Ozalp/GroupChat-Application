/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FXML2Controller implements Initializable {

    @FXML
    private TextField textBarOfHost;
    @FXML
    private Button OKButton;
    @FXML
    private Button cancelButton;

    static String name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void textAction(ActionEvent event) {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        FXML2Controller.name = name;
    }

    @FXML
    private void clickOKActionTwo(ActionEvent event) {
        try {
            Stage stage2 = (Stage) cancelButton.getScene().getWindow();
            stage2.close();

            Stage stage = new Stage();
            name = textBarOfHost.getText();
            System.out.println(name);

            Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Chat App");
            stage.setMinHeight(500);
            stage.setMinWidth(500);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXML2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickCancelActionTwo(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
