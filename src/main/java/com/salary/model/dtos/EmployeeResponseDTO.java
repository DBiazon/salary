package com.salary.model.dtos;

import com.salary.model.EmployeeModel;

public record EmployeeResponseDTO(Long id, String nome, double grossSalary, String codigoUser) {
	
	public EmployeeResponseDTO(EmployeeModel empModel) {
		this(
				empModel.getId(),
				empModel.getNome(),
				empModel.getGrossSalary(), 
				empModel.getCodigoUser()
				);
	}
}
