package TP.LAB5.demo.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.Entity;

@JsonTypeName("drum")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //?
public class Drum  extends Instrument{

    private String color;
    private Integer kitNumber;

}
