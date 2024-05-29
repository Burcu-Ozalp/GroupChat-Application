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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Burcu
 */
public class FXML1Controller implements Initializable {

    @FXML
    private TextField textBarOfHost;
    @FXML
    private Button OKButton;
    @FXML
    private Button cancelButton;

    static int port ;
    
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
        FXML1Controller.port = port;
    }


    @FXML
    private void clickOKActionOne(ActionEvent event) {
        try {
            Stage stage2 = (Stage) cancelButton.getScene().getWindow(); //Logger.getLogger(LoanCalcMain.class.getName()).log(Level.SEVERE, null, ex);
            stage2.close(); 
            
            Stage stage=new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setTitle("Please enter your name");
            stage.setMinHeight(200);
            stage.setMinWidth(400);
            //stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            port=Integer.parseInt(textBarOfHost.getText());
            System.out.println(port);
            stage.show();
            
            
        } catch (IOException ex) {
            //Logger.getLogger(LoanCalcMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickCancelActionOne(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); //Logger.getLogger(LoanCalcMain.class.getName()).log(Level.SEVERE, null, ex);
        stage.close();
    }
    
}
