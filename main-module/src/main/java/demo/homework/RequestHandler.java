package demo.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler {
    public static void handleRequest(Socket socket) {
        ResponseService responseService = new ResponseService();
        Parser parser = new Parser();

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())
        ) {
            String firstLine = input.readLine();
            String fileName = parser.parse(firstLine);

            Path path = Paths.get(ServerConfig.getWWW(), fileName);
            if (!Files.exists(path)) {
                responseService.responseWith404(output);
                return;
            }
            responseService.responseWithFile(output);
            Files.newBufferedReader(path).transferTo(output);
            System.out.println("Client disconnected!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
