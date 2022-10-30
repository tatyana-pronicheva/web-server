package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;
import demo.homework.domain.HttpRequest;
import demo.homework.domain.HttpResponse;

@Handler(order = 2)
public class PostMethodHandler extends MethodHandler{

    public PostMethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        super(next, socketService, responseSerializer);
        super.method = "POST";
    }

    @Override
    public void handleInternal(HttpRequest request) {
        HttpResponse response = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withContentType("text/html")
                .withEncoding("utf-8")
                .build();
        super.writeResponse(response);
    }
}
