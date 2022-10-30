package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;

public class MethodHandlerFactory {
    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer){
        MethodHandler postHandler = new PostMethodHandler(null, socketService, responseSerializer);
        return new GetMethodHandler(postHandler, socketService, responseSerializer);
    }
}
