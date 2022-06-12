package TP.LAB5.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {

    private Class<W> wClass;


    public JsonBodyHandler(Class<W> wClass){
        this.wClass = wClass;
    }

    @Override
    public HttpResponse.BodySubscriber<W> apply(HttpResponse.ResponseInfo responseInfo){
        return asJSON(wClass);
    }


    //No tengo ni puta idea que hace esto la verdad, supongo que matchea un json a un tipo de objeto que yo le pase generico
    public static <T> HttpResponse.BodySubscriber<T> asJSON(Class<T> targetType){
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                (String body) ->{
                    try{
                        ObjectMapper objectMapper = new ObjectMapper();
                        return objectMapper.readValue(body, targetType);
                    }catch(IOException e){
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    public static String convertFromObjetcToJson(Object obj) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
