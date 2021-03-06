package TP.LAB5.demo.controller;

import TP.LAB5.demo.DTO.DTOEmployeeNoShop;
import TP.LAB5.demo.domain.Employee;
import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.services.EmployeeService;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{EmployeeId}")
    public ResponseEntity<Employee> getById(@PathVariable Integer EmployeeId){
        return employeeService.getById(EmployeeId);
    }

    @GetMapping("/a")
    public ResponseEntity<List<DTOEmployeeNoShop>> getAllByShopId(@RequestParam Integer shopId){
        return employeeService.getAllByShopId(shopId);
    }

    @GetMapping("/b")
    public ResponseEntity<Employee> getByNameAndLastName(@RequestParam String name, @RequestParam String lastName){
        return employeeService.getEmployeeByNameAndLastName(name, lastName);
    }

}
