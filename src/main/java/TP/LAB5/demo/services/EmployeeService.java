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
    private String path = "employee";


    public boolean ValidaEmployeeByNameAndLastName(String name, String lastName){
        Employee employee =  employeeRepository.findByNameAndLastName(name, lastName);
        if (employee == null)
            return true;
        else
            return false;
    }

    public PostResponse addEmployee(Employee employee) {
        //Valido que el empleado no sea cargado dos veces
        if (!ValidaEmployeeByNameAndLastName(employee.getName(), employee.getLastName()))
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Este empleado ya existe");

        Employee e = employeeRepository.save(employee);



        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(path, e.getId().toString()))
                .build();
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer EmployeeId) {
        return employeeRepository.findById(EmployeeId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "this Employee not exist"));
    }

    public List<DTOEmployeeNoShop> getAllByShopId(Integer shopId) {
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

         return dtoEmployeeNoShopList;
    }

    public ResponseEntity<Employee> getEmployeeByNameAndLastName(String name, String lastName) {
        Employee employee =  employeeRepository.findByNameAndLastName(name, lastName);
        if (employee == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "this Employee not exist");
        else
            return ResponseEntity.status(HttpStatus.OK).body(employee);


    }
}
