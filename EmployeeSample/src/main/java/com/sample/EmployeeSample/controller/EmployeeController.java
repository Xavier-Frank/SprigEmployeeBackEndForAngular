package com.sample.EmployeeSample.controller;

import com.sample.EmployeeSample.dto.EmployeeDto;
import com.sample.EmployeeSample.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employee Controller", description = "A controller to manage all employee operations")
@CrossOrigin(origins = {"http://localhost:4200"}, methods = RequestMethod.GET)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    @Operation(summary = "An endpoint to add an employee")
    public EmployeeDto addEmployee(@Validated @RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.saveNewEmployee(employeeDto);

        return savedEmployee;
    }

    @GetMapping("/list-of-employees")
    @Operation(summary = "An endpoint to view all employees")
    public List<EmployeeDto> employeeDtoList(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/search-employee/{id}")
    public EmployeeDto findEmployeeById(@PathVariable(value = "id") BigInteger id){
        EmployeeDto searchedEmployee = employeeService.findEmployeeById(id);

        return searchedEmployee;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "An endpoint to delete an employee")
    public Map<String, Boolean> deleteEmployee(@PathVariable BigInteger id){
        employeeService.deleteEmployeeById(id);

        Map<String, Boolean> status = new HashMap<>();
        status.put("Successfully deleted", Boolean.TRUE);

        return status;
    }
    @PutMapping("/{id}")
    @Operation(summary = "An endpoint to edit employee details")
    public EmployeeDto updateEmployee(@PathVariable BigInteger id, @RequestBody EmployeeDto employeeDto){
        return employeeService.editEmployeeDetails(id, employeeDto);
    }


}
