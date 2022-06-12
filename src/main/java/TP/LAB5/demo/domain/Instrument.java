package TP.LAB5.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
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
