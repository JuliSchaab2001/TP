package TP.LAB5.demo.services.impl;

import TP.LAB5.demo.DTO.CurrencyApiDto;
import TP.LAB5.demo.domain.Currency;
import TP.LAB5.demo.services.CurrencyService;
import TP.LAB5.demo.utils.JsonBodyHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CurrencyServiceImpl implements CurrencyService {


    @Override
    public Currency getCurrency() throws IOException, InterruptedException {
        //Creo una request con todo_ lo necesario, methodo, url, credenciales
        // En .method le paso el verbo http y el cuerpo, si el get tiene un cuerpo vacio le aclaro con un .noBody
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-dolar-argentina.herokuapp.com/api/dolarblue")) //
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        //Supongo que no lo tengo que mandar credenciales, o aclararle cosas en el header
        //Lleno el dto con el object maper, respeto los nombre del json en el dto, despues lo paso a mi objeto
        HttpResponse<CurrencyApiDto> response = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(CurrencyApiDto.class));
        return Currency.builder()
                .date(response.body().getFecha().substring(0,9))
                .time(response.body().getFecha().substring(10))
                .buyPrice(Double.valueOf(response.body().getCompra()))
                .salePrice(Double.valueOf(response.body().getVenta()))
                .build();
    }

}
