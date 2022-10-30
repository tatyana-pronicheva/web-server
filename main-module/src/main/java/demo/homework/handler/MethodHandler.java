package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;
import demo.homework.domain.HttpRequest;
import demo.homework.domain.HttpResponse;

import java.io.StringReader;

public abstract class MethodHandler {
    String method;
    private final MethodHandler next;
    private final SocketService socketService;
    private final ResponseSerializer responseSerializer;

    public MethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
    }

    public void handle(HttpRequest request){
        if (method.equals(request.getMethod())){
            handleInternal(request);
        } else if (next!=null){
            next.handle(request);
            return;
        } else {
            HttpResponse response = HttpResponse.createBuilder()
                            .withStatusCode(502)
                            .withEncoding("utf-8")
                            .withContentType("text/html")
                            .withBody("<h1>Метод не поддерживается</h1>")
                            .build();
            socketService.writeResponse(responseSerializer.serialize(response), new StringReader(response.getBody()));
        }
    }

    public abstract void handleInternal(HttpRequest request);

    public void writeResponse(HttpResponse response){
        socketService.writeResponse(responseSerializer.serialize(response), new StringReader(response.getBody()));
    }
    public void writeResponseWithFile(HttpResponse response){
        socketService.writeResponse(responseSerializer.serialize(response), response.getFile());
    }
}
