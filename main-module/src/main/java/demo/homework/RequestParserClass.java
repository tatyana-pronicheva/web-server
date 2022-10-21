package demo.homework;

import demo.homework.domain.HttpRequest;

import java.util.List;

public class RequestParserClass implements RequestParser{
    @Override
    public String parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return parts[1];
    }
}
