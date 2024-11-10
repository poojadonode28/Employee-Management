package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.exception.ResourceNotFoundException;
import com.example.EmployeeManagement.mapper.EmployeeMapper;
import com.example.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.convertToEmployee(employeeDto);
        employeeRepository.save(employee);
        return EmployeeMapper.convertToEmployeeDto(employee);

    }

    public EmployeeDto getEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with given id"));
        return EmployeeMapper.convertToEmployeeDto(employee);
    }

    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::convertToEmployeeDto).toList();
    }

    public EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist"));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employeeRepository.save(employee);
        return EmployeeMapper.convertToEmployeeDto(employee);
    }

    public void deleteEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exist"));
        employeeRepository.deleteById(id);
    }
}
