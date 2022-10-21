package demo.homework.domain;

import java.io.StringReader;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String contentType;
    private String encoding;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText(){
      if (statusCode==404){
          return "NOT_FOUND";
      } else return "OK";
    };

    public String getContentType() {
        return contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getMessage() {
        return message;
    }

    public HttpResponse(int statusCode, String contentType, String encoding) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.encoding = encoding;
    }
}
