package TP.LAB5.demo.services;

import TP.LAB5.demo.domain.Currency;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface CurrencyService {

    Currency getCurrency() throws IOException, InterruptedException;

}
