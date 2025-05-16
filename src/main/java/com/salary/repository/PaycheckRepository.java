package com.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salary.model.EmployeeModel;
import com.salary.model.PayCheckModel;

@Repository
public interface PaycheckRepository extends JpaRepository<PayCheckModel, Long> {
	boolean existsByEmployee(EmployeeModel employee);
}
