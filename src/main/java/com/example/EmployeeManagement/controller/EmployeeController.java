package com.example.EmployeeManagement.controller;


import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @PostMapping("/create/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDtoResponse = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.CREATED);

    }

    @GetMapping("/get/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        EmployeeDto employeeDtoResponse = employeeService.getEmployee(id);
        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.OK);

    }

    @GetMapping("/get/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> employeeDtoResponse = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.OK);

    }

    @PutMapping("/update/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDtoResponse = employeeService.updateEmployee(id,employeeDto);
        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
    }


}
