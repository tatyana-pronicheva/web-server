package demo.homework;

public class WebServer {

    public static void main(String[] args) {
        ServerService serverService = new ServerService();
        serverService.start();
        serverService.handleConnections();
    }
}