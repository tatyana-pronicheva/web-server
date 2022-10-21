package demo.homework;

import demo.homework.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
