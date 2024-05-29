/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Burcu
 */
public class Client {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;
    Socket s;
    String mesage = "";
    boolean stop;
    
    public Client() {
        
    try {
            s = new Socket(FXMLController.getIP(), FXML1Controller.getPort());
            fromServer = new DataInputStream(s.getInputStream());
            toServer = new DataOutputStream(s.getOutputStream());
            // names.add(FXML2Controller.getName());//***
            //listView.getItems().setAll(names);//***
            HandleChat hc = new HandleChat(s);
            new Thread(hc).start();
            messageSending();
            //***********name??
        } catch (Exception ex) {
            System.out.println("Unexpected error happened while creating the socket");
        }
    }

    public void messageSending() throws IOException {

        try {
            toServer.writeUTF(FXML2Controller.getName());
            System.out.println("1");
            toServer.flush();
            System.out.println("2");
            while (s.isConnected()) {
                System.out.println("msg");
                toServer.writeUTF(mesage);
                toServer.flush();
                //msg.setText("deneme");
                //chatArea.appendText(FXML2Controller.getName() + ":" + msg.getText() + "\n");
                System.out.println("3");
                //toServer.writeUTF(FXML2Controller.getName() + ":" + msg.getText() + "\n");
                System.out.println("4");
                //toServer.flush();
                System.out.println("5");
                stop = true;
                /*if (stop) {
                    break;
                }*/
            }
            stop = false;
        } catch (IOException ex) {
            if (s != null) {
                s.close();
            }
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
                    String mssage = fromServer.readUTF();
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
    
    

