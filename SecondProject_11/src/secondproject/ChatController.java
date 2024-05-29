/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Burcu
 */
public class ChatController implements Initializable {

    @FXML
    private TextField msg;
    @FXML
    private TextArea chatArea;
    @FXML
    private ListView<String> listView;

    ArrayList<String> names2 = new ArrayList<>();

    private static DataInputStream fromServer;
    private static DataOutputStream toServer;
    Socket s;
    String mesage = "";
    boolean stop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //names2.add(FXML2Controller.getName());
        //listView.getItems().setAll(names2);
    }

    @FXML
    private void enter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            //chatArea.appendText(FXML2Controller.getName() + ":" + msg.getText() + "\n");
            //mesage = FXML2Controller.getName() + ":" + msg.getText() + "\n";
            //msg.setText("");
            toServer.writeInt(2);
            toServer.writeUTF(FXML2Controller.getName());
            toServer.writeUTF(msg.getText());
            toServer.flush();
            //chatArea.appendText(fromServer.readUTF());
            //fromServer.readUTF();
            mesage = msg.getText();

            msg.setText("");
        }
    }

    private String getmsg() {
        return chatArea.getText();
    }

    public ChatController() {
        try {
            s = new Socket(FXMLController.getIP(), FXML1Controller.getPort());
            fromServer = new DataInputStream(s.getInputStream());
            toServer = new DataOutputStream(s.getOutputStream());
            toServer.writeInt(1);
            toServer.writeUTF(FXML2Controller.getName());
            toServer.flush();
            // names.add(FXML2Controller.getName());//***
            //listView.getItems().setAll(names);//***
            HandleChat hc = new HandleChat(s);
            new Thread(hc).start();
            // messageSending();
            //***********name??
        } catch (Exception ex) {
            System.out.println("Unexpected error happened while creating the socket");
        }
    }

  
    class HandleChat implements Runnable {

        //private Socket socket; // A connected socket
        public HandleChat(Socket socket) {
            //this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int action = fromServer.readInt();
                    if (action == 1) {
                        String names = fromServer.readUTF();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(!names2.contains(names)){
                                names2.add(names);
                                listView.getItems().setAll(names2); }
                            }
                        });
                    } else if (action == 2) {
                        String chat = fromServer.readUTF();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("hi");
                                //String chat = fromServer.readUTF();
                                //String names = fromServer.readUTF();
                                //chatArea.appendText(FXML2Controller.getName() + ":" + msg.getText() + "\n");
                                chatArea.setText( chat + "\n");
                            }
                        });
                    }
                    //String mssage = fromServer.readUTF();
                    //toServer.writeUTF(FXML2Controller.getName());
                    //toServer.flush();
                    //chatArea.appendText(FXML2Controller.getName() + ":" + mssage + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
