package demo.homework;

import demo.homework.domain.HttpRequest;
import demo.homework.domain.HttpResponse;
import demo.homework.logger.Logger;
import demo.homework.logger.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final Logger logger = LoggerFactory.createLogger();

    private final SocketService socketService;
    private final RequestParser requestParser = new RequestParserClass();
    private final ResponseSerializer responseSerializer = new ResponseSerializerClass();

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();

        HttpRequest httpRequest = requestParser.parse(request);
        Path path = httpRequest.getPath();
        System.out.println(path);

        if (!Files.exists(path)) {
            HttpResponse response = HttpResponse.createBuilder()
                    .withStatusCode(404)
                    .withContentType("text/html").
                    withEncoding("utf-8")
                    .build();
            socketService.writeResponse(responseSerializer.serialize(response), new StringReader("<h1>Файл не найден!</h1>\n"));
            return;
        }

        try {
            HttpResponse response = HttpResponse.createBuilder()
                    .withStatusCode(200)
                    .withContentType("text/html")
                    .withEncoding("utf-8")
                    .build();
            socketService.writeResponse(responseSerializer.serialize(response), Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
