package TP.LAB5.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class DTOInstrumentNoShop {

    private Integer id;
    private Boolean isNew;
    private Double dolarPrice;
    private Double pesosPrice;
    private String brand;

}
