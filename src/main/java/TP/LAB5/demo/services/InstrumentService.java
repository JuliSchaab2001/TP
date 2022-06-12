package TP.LAB5.demo.services;

import TP.LAB5.demo.domain.Currency;
import TP.LAB5.demo.domain.Instrument;
import TP.LAB5.demo.repository.InstrumentRepository;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

import static TP.LAB5.demo.utils.EntityURLBuilder.buildURL;

@Service
public class InstrumentService {


    private static String path = "instrument";
    @Autowired
    private InstrumentRepository instrumentRepository;

    //Esta bien llamar servicios dentro de servicios?
    //Hace falta que le ponga el autowired a esta mierda?
    @Autowired
    private CurrencyService currencyService;

    public PostResponse addInstrument(Instrument instrument) {

        //Creo el instrumento y lo guardo en una variable
        Instrument I = instrumentRepository.save(instrument);

        //Retorno un PostResponse y utilizo el instrumento guardado Para crear la url y pasarsela al postResponse
        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(path,I.getId().toString())).build();
    }

    public List<Instrument> getAll() {
        try {
            Currency currency = currencyService.getCurrency();
        List<Instrument> instrumentList =  instrumentRepository.findAll();
        for(Instrument instrument: instrumentList){
            instrument.setPesosPrice(instrument.getDolarPrice() * currency.getSalePrice());
        }
        return instrumentList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Instrument getInstrumentById(final Integer id)
    {
    try{
        Currency currency = currencyService.getCurrency();
        Instrument instrument =  instrumentRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Instrument not exist"));

        instrument.setPesosPrice(instrument.getDolarPrice() * currency.getSalePrice());

        return instrument;

    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }







}
