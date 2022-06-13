package TP.LAB5.demo.repository;

import TP.LAB5.demo.DTO.DTOEmployeeNoShop;
import TP.LAB5.demo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByShopId(Integer shopId);

    Employee findByNameAndLastName(String name, String lastName);
}
