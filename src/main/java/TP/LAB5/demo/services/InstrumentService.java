package TP.LAB5.demo.services;

import TP.LAB5.demo.DTO.DTODrumNoShop;
import TP.LAB5.demo.DTO.DTOGuitarNoShop;
import TP.LAB5.demo.DTO.DTOInstrumentNoShop;
import TP.LAB5.demo.domain.Currency;
import TP.LAB5.demo.domain.Drum;
import TP.LAB5.demo.domain.Guitar;
import TP.LAB5.demo.domain.Instrument;
import TP.LAB5.demo.repository.InstrumentRepository;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity addInstrument(Instrument instrument) {

        //Creo el instrumento y lo guardo en una variable
        Instrument I =  instrumentRepository.save(instrument);


        return ResponseEntity.status(HttpStatus.OK).location(buildURL(path,I.getId().toString())).build();
    }

    public ResponseEntity<List<Instrument>> getAll(){
        try {
            ResponseEntity <Currency> currency = currencyService.getCurrency();
        List<Instrument> instrumentList =  instrumentRepository.findAll();
        for(Instrument instrument: instrumentList){
            instrument.setPesosPrice(instrument.getDolarPrice() * currency.getBody().getSalePrice());
        }
        return ResponseEntity.status(HttpStatus.OK).body(instrumentList);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Instrument> getInstrumentById(final Integer id)
    {
    try{
        ResponseEntity <Currency> currency = currencyService.getCurrency();
        Instrument instrument =  instrumentRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Instrument not exist"));

        instrument.setPesosPrice(instrument.getDolarPrice() * currency.getBody().getSalePrice());

        return ResponseEntity.status(HttpStatus.OK).body(instrument);

    } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
    }
    }

    public ResponseEntity<List<DTOInstrumentNoShop>> getInstrumentByShopId(Integer shopId) {
        try {
        List<DTOInstrumentNoShop> dtoInstrumentNoShops = new ArrayList<DTOInstrumentNoShop>();
        List<Instrument> instrumentList = instrumentRepository.findAllByShopId(shopId);
        ResponseEntity <Currency> currency = currencyService.getCurrency();
        //paso la lista a una lista de dto para no devolver los shops, tambien asigno pesosPrice

        for (Instrument instrument: instrumentList){

            if(instrument.getClass() == Drum.class){
            dtoInstrumentNoShops.add(DTODrumNoShop
                    .builder()
                    .id(instrument.getId())
                    .isNew(instrument.getIsNew())
                    .brand(instrument.getBrand())
                    .dolarPrice(instrument.getDolarPrice())
                    .pesosPrice(instrument.getDolarPrice() *currency.getBody().getSalePrice())
                    .color(((Drum) instrument).getColor())
                    .kitNumber(((Drum) instrument).getKitNumber())
                    .build());
            }

            if(instrument.getClass() == Guitar.class){
                dtoInstrumentNoShops.add(DTOGuitarNoShop
                        .builder()
                        .id(instrument.getId())
                        .isNew(instrument.getIsNew())
                        .brand(instrument.getBrand())
                        .dolarPrice(instrument.getDolarPrice())
                        .pesosPrice(instrument.getDolarPrice() *currency.getBody().getSalePrice())
                        .strNumber(((Guitar) instrument).getStrNumber())
                        .type(((Guitar) instrument).getType())
                        .build());
            }

        }

        return ResponseEntity.status(HttpStatus.OK).header("Status", "OK").body(dtoInstrumentNoShops);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}













