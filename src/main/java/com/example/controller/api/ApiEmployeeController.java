package com.example.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Employee;
import com.example.form.SearchEmployeeAutoCompleteForm;
import com.example.form.SearchEmployeeForm;
import com.example.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.postgresql.core.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class ApiEmployeeController {
    
    private final EmployeeService employeeService;

    @GetMapping({"/",""})
    public ResponseEntity<List<Employee>> getNameAutoComplete(SearchEmployeeForm form){
        Employee employee = new Employee();
        BeanUtils.copyProperties(form, employee);
        int limit = form.getLimit();
        if (limit == 0) {
            limit = 10;
        }
        List<Employee> employees = employeeService.findEmployeeNameByName(form, limit,0 );
        return new ResponseEntity(employees,HttpStatus.OK);
    }
}
