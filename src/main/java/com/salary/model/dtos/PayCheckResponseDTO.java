package com.salary.model.dtos;

import com.salary.model.EmployeeModel;
import com.salary.model.PayCheckModel;

public record PayCheckResponseDTO(Long id, double netSalary, EmployeeModel employeeModel) {

	public PayCheckResponseDTO(PayCheckModel checkModel) {
		this(
				checkModel.getId(),
				checkModel.getNetSalary(),
				checkModel.getEmployee());
	};

}
