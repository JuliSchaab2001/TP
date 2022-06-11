package TP.LAB5.demo.utils;

//Desde el controller voy a devolver esto que contiene una url del elemento posteado y un statuscode

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class PostResponse {

    private String link;
    private HttpStatus httpStatus;

}
