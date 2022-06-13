package TP.LAB5.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DTOGuitarNoShop extends DTOInstrumentNoShop{

    private Integer strNumber;
    private String type;

}
