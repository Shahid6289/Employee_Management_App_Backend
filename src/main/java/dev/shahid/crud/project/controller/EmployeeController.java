package dev.shahid.crud.project.controller;

import dev.shahid.crud.project.model.Employee;
import dev.shahid.crud.project.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

//    Get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

//    get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return ResponseEntity.ok(employee);
    }

//    update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

//    delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
