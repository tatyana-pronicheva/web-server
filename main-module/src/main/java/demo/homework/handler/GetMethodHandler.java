package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;
import demo.homework.domain.HttpRequest;
import demo.homework.domain.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Handler(order = 1)
public class GetMethodHandler extends MethodHandler {

    public GetMethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        super(next, socketService, responseSerializer);
        super.method = "GET";
    }

    public void handleInternal(HttpRequest request){
        Path path = request.getPath();
        if (!Files.exists(path)) {
            HttpResponse response = HttpResponse.createBuilder()
                    .withStatusCode(404)
                    .withContentType("text/html")
                    .withEncoding("utf-8")
                    .withBody("<h1>Файл не найден!</h1>\n")
                    .build();
            super.writeResponse(response);
        }

        try {
            HttpResponse response = HttpResponse.createBuilder()
                    .withStatusCode(200)
                    .withContentType("text/html")
                    .withEncoding("utf-8")
                    .withFile(Files.newBufferedReader(path))
                    .build();
            super.writeResponseWithFile(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
