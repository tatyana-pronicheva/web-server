package demo.homework;

import demo.homework.domain.HttpRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestParserClass implements RequestParser{
    @Override
    public HttpRequest parse(List<String> request) {
        String[] firstLine = request.get(0).split(" ");
        String method = firstLine[0];
        String fileName = firstLine[1];

        request.remove(0);
        Map<String, String> headers = request.stream()
                        .collect(Collectors.toMap(
                                        (line)->{
                                            return line.split(": ",2)[0];
                                        },
                                        (line)->{
                                            String value = "";
                                            try {
                                                value = line.split(": ")[1];
                                            } catch (ArrayIndexOutOfBoundsException e){
                                                e.printStackTrace();
                                            }
                                            return value;
                                         }
                                ));
        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withMethod(method)
                .withFileName(fileName)
                .withHeaders(headers)
                .build();

        return httpRequest;
    }
}
