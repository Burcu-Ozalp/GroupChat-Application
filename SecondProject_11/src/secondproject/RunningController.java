package secondproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Burcu, Kubra
 */
public class RunningController implements Initializable {

    @FXML
    private TextArea textPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textPart.appendText(Integer.toString(PortAddressOfServerController.getPort()));
        textPart.setEditable(false);

    }

}
