package demo.homework;

import demo.homework.handler.RequestHandler;
import demo.homework.logger.Logger;
import demo.homework.logger.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFactory {
    private static final Logger logger = LoggerFactory.createLogger();

    public static void createServer(){
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                SocketService socketService = new SocketService(socket);
                ResponseSerializer responseSerializer = new ResponseSerializerClass();
                new Thread(new RequestHandler(socketService,
                       responseSerializer)
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
