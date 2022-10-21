package demo.homework;
import demo.homework.domain.HttpResponse;
import demo.homework.logger.ConsoleLogger;
import demo.homework.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "/Users/aleks/dev/geek-architecture-123/www";

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;
    private final RequestParser requestParser = new RequestParserClass();
    private final ResponseSerializer responseSerializer = new ResponseSerializerClass();

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();

        String fileName = requestParser.parse(request);

        Path path = Paths.get(WWW, fileName);
        if (!Files.exists(path)) {
            HttpResponse response = new HttpResponse(404, "text/html", "uft-8");
            socketService.writeResponse(responseSerializer.serialize(response), new StringReader("<h1>Файл не найден!</h1>\n"));
            return;
        }

        try {
            HttpResponse response = new HttpResponse(200, "text/html", "utf-8");
            socketService.writeResponse(responseSerializer.serialize(response), Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
