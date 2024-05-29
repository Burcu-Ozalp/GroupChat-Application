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
 * FXMLController Controller class
 *
 * @author Burcu, Kubra
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField textBarOfHost;
    @FXML
    private Button OKButton;
    @FXML
    private Button cancelButton;

    static String IP;

    /**
     * Initializes the controller class.
     */

    public FXMLController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        FXMLController.IP = IP;
    }

    @FXML
    private void clickOKAction(ActionEvent event) {
        try {
            Stage stage2 = (Stage) cancelButton.getScene().getWindow();
            stage2.close();

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Enter port address of server");
            stage.setMinHeight(200);
            stage.setMinWidth(400);
            IP = textBarOfHost.getText();
            System.out.println("*" + IP);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickCancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
