package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;
import demo.homework.domain.HttpRequest;
import demo.homework.domain.HttpResponse;

@Handler(order = 0)
public class TestMethodHandler extends MethodHandler{

    public TestMethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        super(next, socketService, responseSerializer);
        super.method = "GET";
    }

    @Override
    public void handleInternal(HttpRequest request) {
        HttpResponse response = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withContentType("text/html")
                .withEncoding("utf-8")
                .withBody("Ответ от тестового handler")
                .build();
        super.writeResponse(response);
    }
}
