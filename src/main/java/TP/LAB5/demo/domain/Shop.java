package TP.LAB5.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;


    @OneToOne(mappedBy = "shop")
    @JsonIgnore
    private Employee employee;

    @OneToMany(mappedBy = "shop")
    @JsonIgnore
    private List<Instrument> instrumentList;    //Va la clase padre? o necesito las individuales tipo guitarra o bateria?


}
