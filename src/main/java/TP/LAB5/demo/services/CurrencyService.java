package TP.LAB5.demo.services;

import TP.LAB5.demo.domain.Currency;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface CurrencyService {

    ResponseEntity<Currency> getCurrency() throws IOException, InterruptedException;

}
