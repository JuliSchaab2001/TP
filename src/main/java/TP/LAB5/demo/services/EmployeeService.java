package TP.LAB5.demo.services;


import TP.LAB5.demo.DTO.DTOEmployeeNoShop;
import TP.LAB5.demo.domain.Employee;
import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.repository.EmployeeRepository;
import TP.LAB5.demo.utils.PostResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static TP.LAB5.demo.utils.EntityURLBuilder.buildURL;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShopService shopService; //Deberia ser Autowired?
    private String path = "employee";


    public ResponseEntity addEmployee(Employee employee) {
        //Valido que el empleado no sea cargado dos veces
        if (getEmployeeByNameAndLastName(employee.getName(), employee.getLastName()).getBody() != null)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Este empleado ya existe");
        shopService.getById(employee.getShop().getId()); //Si no encuentra el shop se va a romper

        Employee e = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).location(buildURL(path, e.getId().toString())).build();
    }

    public ResponseEntity<List<Employee>> getAll() {

        List<Employee> employeeList = employeeRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    public ResponseEntity<Employee> getById(Integer employeeId) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "this Employee not exist"));

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    public ResponseEntity<List<DTOEmployeeNoShop>> getAllByShopId(Integer shopId) {
         List<DTOEmployeeNoShop> dtoEmployeeNoShopList = new ArrayList<DTOEmployeeNoShop>();
         List<Employee> employeeList = employeeRepository.findByShopId(shopId);

         for(Employee employee: employeeList){
             dtoEmployeeNoShopList.add(DTOEmployeeNoShop
                     .builder()
                     .id(employee.getId())
                     .age(employee.getAge())
                     .name(employee.getName())
                     .lastName(employee.getLastName())
                     .build());
         }

         return ResponseEntity.status(HttpStatus.OK).body(dtoEmployeeNoShopList);
    }

    public ResponseEntity<Employee> getEmployeeByNameAndLastName(String name, String lastName) {
        Employee employee =  employeeRepository.findByNameAndLastName(name, lastName);
        if (employee == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(employee);


    }
}
