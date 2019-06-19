package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	public Employee findById(int id) {
		Employee emp= employeeRepository.findById(id).orElse(null);
		if(emp==null) {
			throw new RuntimeException("Unable to find employee of id: "+id);
		}
		return emp;
	}
	public void save(Employee emp) {
		employeeRepository.save(emp);
	}
	
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}
}
