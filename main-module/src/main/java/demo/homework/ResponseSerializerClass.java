package demo.homework;

import demo.homework.domain.HttpResponse;

public class ResponseSerializerClass implements ResponseSerializer {
    @Override
    public String serialize(HttpResponse httpResponse) {
        return  "HTTP/1.1 "+httpResponse.getStatusCode()+" "+httpResponse.getStatusText()+" \n" +
                "Content-Type: "+httpResponse.getContentType()+"; charset="+httpResponse.getEncoding()+"\n" +
                "\n";
    }
}
