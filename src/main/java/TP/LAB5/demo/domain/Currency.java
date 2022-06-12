package TP.LAB5.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Currency {

    private String date;
    private String time;
    private double buyPrice;
    private double salePrice;

}
