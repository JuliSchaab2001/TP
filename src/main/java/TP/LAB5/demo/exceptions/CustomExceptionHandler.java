package TP.LAB5.demo.exceptions;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Preguntar o averiguar por estas dos anotaciones
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //Por lo que entendi, si tira una excepcion del tipo HttpClientErrorException, este metodo las cachea, yo le asigno valores a la hora de tirarla, en el service
    //Este return a donde va? pasa por el controller o ya lo devuelve directamente?
    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorException(final HttpClientErrorException ex, final WebRequest request){
        return new ResponseEntity(ErrorBody.builder()
                .code(ex.getStatusCode().value())
                .message(ex.getStatusText())
                .build(), ex.getStatusCode());
    }

}
