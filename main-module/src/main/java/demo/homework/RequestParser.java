package demo.homework;

import demo.homework.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    String parse(List<String> rawRequest);
}
