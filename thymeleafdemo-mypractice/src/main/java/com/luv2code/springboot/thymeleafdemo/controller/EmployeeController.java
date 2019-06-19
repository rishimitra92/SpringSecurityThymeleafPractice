package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	/*private List<Employee> employees;
	@PostConstruct
	private void loadData() {
		Employee emp1=new Employee(1,"Rishi","Mitra","rishi@luv2code.com");
		Employee emp2=new Employee(2,"Walter","White","walter@luv2code.com");
		Employee emp3=new Employee(3,"Leslie","Andrews","leslie@luv2code.com");
		employees=new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
	}*/
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String employeeList(Model theModel) {
		List<Employee> employees=employeeService.findAll();
		theModel.addAttribute("employees" ,employees);
		return "employee/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee employee=new Employee();
		theModel.addAttribute("employee" ,employee);
		return "employee/employee-form";
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		employeeService.save(emp);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,Model theModel) {
		Employee employee=employeeService.findById(id);
		theModel.addAttribute("employee" ,employee);
		return "employee/employee-form";
	}
	@GetMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("employeeId") int id) {
		employeeService.deleteById(id);
		
		return "redirect:/employees/list";
	}
	
	
}
