package com.sample.EmployeeSample.service;

import com.sample.EmployeeSample.dto.EmployeeDto;
import com.sample.EmployeeSample.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Transactional
public interface EmployeeService {

    /**
     *
     * @param employeeDto - employee details to be saved to the database
     * @return employee details saved
     */
    EmployeeDto saveNewEmployee(EmployeeDto employeeDto);

    /**
     *
     * @param id - id to be used to find the employee
     * @return
     */
    EmployeeDto findEmployeeById(BigInteger id);

    /**
     *
     * @param id employee id to be used to delete the employee
     * @return
     */
    Boolean deleteEmployeeById(BigInteger id);

    /**
     *
     * @param id - employee id to be used to edit details
     * @param employeeDto details to be passed
     * @return updtaed employee
     */
    EmployeeDto editEmployeeDetails(BigInteger id, EmployeeDto employeeDto);

    /**
     *
     * @return - a list of employees
     */
    List<EmployeeDto> getAllEmployees();
}
