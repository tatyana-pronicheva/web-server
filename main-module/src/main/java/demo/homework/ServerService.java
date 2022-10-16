package demo.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService {
    private static ServerSocket serverSocket;
    public RequestHandler requestHandler = new RequestHandler();

    public void start(){
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnections(){
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new Thread(() -> requestHandler.handleRequest(socket)).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
