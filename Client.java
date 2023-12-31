package com.example.client_side;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error creating client");
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessageToServer(String msgToServer){
        try{
            bufferedWriter.write(msgToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message to server");
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void receiveMessageFromServer(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()) {
                    try{
                        String messageFromServer = bufferedReader.readLine();
                        Controller.addLabel(messageFromServer,vBox);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Receiving message from the server error");
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(bufferedReader != null) bufferedReader.close();
            if(bufferedWriter != null) bufferedWriter.close();
            if(socket != null) socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
