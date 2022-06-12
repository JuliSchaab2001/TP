package TP.LAB5.demo.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.Entity;

@JsonTypeName("guitar")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //?
public class Guitar extends Instrument{

    private Integer strNumber;
    private String type;

}
