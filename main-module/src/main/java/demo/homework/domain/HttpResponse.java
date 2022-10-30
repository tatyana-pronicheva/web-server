package demo.homework.domain;

import java.io.BufferedReader;

public class HttpResponse {

    private int statusCode;
    private String contentType;
    private String encoding;
    private String body;
    private BufferedReader file;

    public BufferedReader getFile() {
        return file;
    }

    public String getBody() {
        return body;
    }

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

    public static Builder createBuilder(){
        return new Builder();
    }

    private HttpResponse() {}

    public static class Builder {
        private final HttpResponse httpResponse;

        public Builder(){
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode){
            this.httpResponse.statusCode = statusCode;
            return this;
        }
        public Builder withContentType(String contentType){
            this.httpResponse.contentType = contentType;
            return this;
        }
        public Builder withEncoding(String encoding){
            this.httpResponse.encoding = encoding;
            return this;
        }
        public Builder withBody(String body){
            this.httpResponse.body = body;
            return this;
        }
        public Builder withFile(BufferedReader file){
            this.httpResponse.file = file;
            return this;
        }
        public HttpResponse build(){
            return this.httpResponse;
        }
    }
}
