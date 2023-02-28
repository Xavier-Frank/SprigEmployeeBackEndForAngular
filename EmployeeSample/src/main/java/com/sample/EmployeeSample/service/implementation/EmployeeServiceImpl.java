package com.sample.EmployeeSample.service.implementation;

import com.sample.EmployeeSample.dto.EmployeeDto;
import com.sample.EmployeeSample.repository.EmployeeRepository;
import com.sample.EmployeeSample.service.EmployeeService;
import com.sample.EmployeeSample.entity.Employee;
import com.sample.EmployeeSample.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //convert entity to dto
    public EmployeeDto convertEntityToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setUsername(employee.getUsername());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());

        return employeeDto;
    }

    //convert dto to entity
    public Employee convertDtoToEntity(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setUsername(employeeDto.getUsername());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());

        return employee;
    }
    @Override
    public EmployeeDto saveNewEmployee(EmployeeDto employeeDto) {
        log.info("Saving a new Employee");

        Employee newEmployee = convertDtoToEntity(employeeDto);
        employeeRepository.save(newEmployee);

        log.info("User save successfully");

        return convertEntityToDto(newEmployee);

    }

    @Override
    public EmployeeDto findEmployeeById(BigInteger id) {
        log.info("Retrieving employee with id " + id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee with id : " + id + " is not found"));

        EmployeeDto searchedEmployee = convertEntityToDto(employee);

        return searchedEmployee;
    }
    @Override
    public Boolean deleteEmployeeById(BigInteger id) {
        log.info("Deleting a user...");

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee with id : " + id + " is not found"));

        employeeRepository.delete(employee);

        log.info("Successfully deleted a user");
        return true;
    }

    @Override
    public EmployeeDto editEmployeeDetails(BigInteger id, EmployeeDto employeeDto ) {
        log.info("Editing employee details");
        Employee employeeToEdit = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee with id : " + id + " is not found"));

        employeeToEdit.setUsername(employeeDto.getUsername());
        employeeToEdit.setAge(employeeDto.getAge());
        employeeToEdit.setEmail(employeeDto.getEmail());
        employeeToEdit.setPassword(employeeDto.getPassword());

        Employee finalEmployee = employeeRepository.save(employeeToEdit);

        final EmployeeDto updatedEmployee = convertEntityToDto(finalEmployee);

        log.info("Employee details successfully deleted");
        return updatedEmployee;
    }
    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("Retrieving a list of employees");

        return employeeRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
