package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.Employee;
import com.web.service.EmployeeService;

@Controller
public class EmployeeController {
	
	
	//display list of employees
	
    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }
    
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
    	Employee employee = new Employee();
    	model.addAttribute("employee", employee);
    	return "new_employee";
    }
    
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    	
    	employeeService.saveEmployee(employee);
    	return "redirect:/";
    	
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
    	// get employee from service
    	Employee employee = employeeService.getEmployeeById(id);
    	
    	//set employee as a model attribute to pre-populate the form
    	model.addAttribute("employee", employee);
    	return "update_employee";
    	
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable ( value = "id") long id, Model model) {
    	
    	//call delete method
    	
    	this.employeeService.deleteEmployeeById(id);
    	return "redirect:/";
    }

}
