package TP.LAB5.demo.controller;


import TP.LAB5.demo.DTO.DTOInstrumentNoShop;
import TP.LAB5.demo.domain.Instrument;
import TP.LAB5.demo.services.InstrumentService;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    //Estandar de Rest devuelve lo siguiente al dar de alta un instrumento
    // /localhost:8080/instrument/{id Instrumento}
    // StatusCode: 201(Created)

    @PostMapping("/")
    //Le paso el json como parametro @RequestBody
    //ahora devuelvo un PostResponse
    public ResponseEntity addInstrument(@RequestBody final Instrument instrument){

        return instrumentService.addInstrument(instrument);

    }

    @GetMapping("/")
    public ResponseEntity<List<Instrument>> getInstruments(){
        return instrumentService.getAll();
    }

    //Get by id, paso el id por url, pongo entre {} el nombre que tengo que respetar en el parametro
    @GetMapping("/{InstrumentId}")
    //Le digo con @PathVariable que la variable llega por URL
    //REST-> poner final las variables recibidas por parametro
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable final Integer InstrumentId){
        return instrumentService.getInstrumentById(InstrumentId);
    }

    //Devuelvo un Response Entity desde el Servicio, pa ver que onda
    @GetMapping("/a")
    public ResponseEntity<List<DTOInstrumentNoShop>> getInstrumentByShopId(@RequestParam Integer shopId){
        return instrumentService.getInstrumentByShopId(shopId);
    }
}
























