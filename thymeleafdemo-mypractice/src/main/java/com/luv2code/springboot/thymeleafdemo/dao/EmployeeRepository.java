package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.stereotype.Repository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
}