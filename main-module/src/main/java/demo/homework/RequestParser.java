package demo.homework;

import demo.homework.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    HttpRequest parse(List<String> rawRequest);
}
