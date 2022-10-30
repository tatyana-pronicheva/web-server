package demo.homework.handler;

import demo.homework.RequestParser;
import demo.homework.RequestParserClass;
import demo.homework.ResponseSerializer;
import demo.homework.SocketService;
import demo.homework.domain.HttpRequest;
import demo.homework.logger.Logger;
import demo.homework.logger.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final Logger logger = LoggerFactory.createLogger();
    private final SocketService socketService;
    private final RequestParser requestParser = new RequestParserClass();
    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService, ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(request);
        new HandlerCreator(socketService, responseSerializer).getFirstMethodHandler().handle(httpRequest);

        try{
            socketService.close();
            logger.info("Client disconnected!");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
