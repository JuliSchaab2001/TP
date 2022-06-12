package TP.LAB5.demo.domain;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type") //Propiedad que despues va al json para indicar el tipo
@JsonSubTypes({
        @JsonSubTypes.Type(value = Guitar.class, name = "guitar"),
       @JsonSubTypes.Type(value = Drum.class, name = "drum")
})
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isNew;
    private Double dolarPrice;
    private Double pesosPrice;
    private String brand;

    @ManyToOne()
    @JoinColumn(name = "shop_id") //Le digo el nombre que va a llevar la columna de FK
    private Shop shop;

}
