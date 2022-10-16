package demo.homework;

import java.io.PrintWriter;

public class ResponseService {
    public void responseWith404(PrintWriter output){
        output.println("HTTP/1.1 404 NOT_FOUND");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<h1>Файл не найден!</h1>");
        output.flush();
    }

    public void responseWithFile(PrintWriter output) {
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
    }
}
