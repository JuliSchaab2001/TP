package TP.LAB5.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOEmployeeNoShop {

    private Integer id;
    private String name;
    private String lastName;
    private Integer age;

}
