package TP.LAB5.demo.controller;

import TP.LAB5.demo.domain.Currency;
import TP.LAB5.demo.services.CurrencyService;
import TP.LAB5.demo.services.impl.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    //No tengo ni puta idea por que hace esto y no un autowired comun, supongo que es por que no es una clase si no una interfaz
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController (CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    //Estas exepciones no las cachea el CustomHandler?
    @GetMapping("/")
    public Currency getCurrency() throws IOException, InterruptedException {
        return currencyService.getCurrency();
    }

}
