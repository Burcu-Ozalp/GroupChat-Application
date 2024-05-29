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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Burcu, Kubra
 */
public class Server {

    static StringBuffer chatmsg = new StringBuffer();
    private List<HandleAClient> users = Collections.synchronizedList(new ArrayList<>());

    public Server() {
        try {

            ServerSocket serverSocket = new ServerSocket(PortAddressOfServerController.getPort());
            InetAddress inetAdd = serverSocket.getInetAddress();
            System.out.println(inetAdd);

            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("A person is added");
                InetAddress inetAddress = s.getInetAddress();
                HandleAClient task = new HandleAClient(s);
                users.add(task);
                new Thread(task).start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    class HandleAClient implements Runnable {

        private Socket socket; 
        private DataOutputStream outputToClient;

        public HandleAClient(Socket socket) {
            this.socket = socket;
            try {
                this.outputToClient = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());

                while (true) {
                    String template = "";
                    int action = inputFromClient.readInt();
                    if (action == 1) {
                        String name = inputFromClient.readUTF();
                        System.out.println(name + " joined chat");
                        updateUserList(name);
                    } else if (action == 2) {

                        String person = inputFromClient.readUTF();
                        String msg = inputFromClient.readUTF();
                        chatmsg.append(person + " : " + msg + "\n");
                        System.out.println("denmee " + msg);
                        updateUserList(person);
                        updateChatMessage(chatmsg.toString());

                        outputToClient.flush();

                    }
                    if (!template.equals(chatmsg.toString())) {
                        outputToClient.writeInt(2);
                        outputToClient.writeUTF(chatmsg.toString());
                        template = chatmsg.toString();
                    }

                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        private void updateUserList(String newUser) throws IOException {

            for (HandleAClient client : users) {
                DataOutputStream clientOutput = client.outputToClient;
                clientOutput.writeInt(1);
                clientOutput.writeUTF(newUser);
                clientOutput.flush();
            }

        }

        private void updateChatMessage(String message) throws IOException {

            for (HandleAClient client : users) {
                DataOutputStream clientOutput = client.outputToClient;
                clientOutput.writeInt(2);
                clientOutput.writeUTF(message);
                clientOutput.flush();
            }

        }
    }

}
