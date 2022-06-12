package TP.LAB5.demo.services;


import TP.LAB5.demo.domain.Employee;
import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.repository.EmployeeRepository;
import TP.LAB5.demo.utils.PostResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static TP.LAB5.demo.utils.EntityURLBuilder.buildURL;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private String path = "employee";


    public PostResponse addEmployee(Employee employee) {
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
}
