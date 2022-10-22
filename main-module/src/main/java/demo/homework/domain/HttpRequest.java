package demo.homework.domain;

import demo.homework.Config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class HttpRequest {

    private String method;

    private Path path;

    private String fileName;

    private Map<String, String> headers;

    private String body;

    public String getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    private HttpRequest() {}

    public static Builder createBuilder(){
        return new Builder();
    }

    public static class Builder{
        private HttpRequest httpRequest = new HttpRequest();
        public HttpRequest build(){
            return this.httpRequest;
        }
        public Builder withMethod(String method){
            this.httpRequest.method = method;
            return this;
        }
        public Builder withFileName(String fileName){
            this.httpRequest.fileName = fileName;
            this.httpRequest.path = Paths.get(Config.getWWW(), fileName);
            return this;
        }
        public Builder withHeaders(Map<String, String> headers){
            this.httpRequest.headers = headers;
            return this;
        }
        public Builder withBody(String body){
            this.httpRequest.body = body;
            return this;
        }

    }
}
