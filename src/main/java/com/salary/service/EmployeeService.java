package com.salary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salary.exceptions.ResourceAlreadyRegisteredException;
import com.salary.exceptions.ResourceNotFoundException;
import com.salary.model.EmployeeModel;
import com.salary.model.dtos.EmployeeInsertDTO;
import com.salary.model.dtos.EmployeeResponseDTO;
import com.salary.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)
	public List<EmployeeResponseDTO> findAllEMployee() {

		List<EmployeeModel> listEmployee = employeeRepository.findAll();
		EmployeeResponseDTO employeeDto;
		List<EmployeeResponseDTO> listEmployeeDto = new ArrayList<>();

		for (EmployeeModel employeeModel : listEmployee) {
			employeeDto = new EmployeeResponseDTO(employeeModel);

			listEmployeeDto.add(employeeDto);
		}

		return listEmployeeDto;
	}

	@Transactional(readOnly = true)
	public EmployeeResponseDTO getOneEmployee(Long id) {

		EmployeeModel employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionário de id %s não encontrado!".formatted(id)));

		EmployeeResponseDTO emResponseDTO = new EmployeeResponseDTO(employee);

		return emResponseDTO;
	}

	@Transactional
	public EmployeeModel createEmployee(EmployeeInsertDTO employeeInsertDTO) {

		EmployeeModel employeeModel = new EmployeeModel();

		if (this.existsByCodigoUser(employeeInsertDTO.codigoUser())) {
			throw new ResourceAlreadyRegisteredException(
					"Funcionário com o código: %s já cadastrado".formatted(employeeInsertDTO.codigoUser()));
		}
		
		BeanUtils.copyProperties(employeeInsertDTO, employeeModel);
		return employeeRepository.save(employeeModel);
	}

	private boolean existsByCodigoUser(String codigoUser) {
		return this.employeeRepository.existsByCodigoUser(codigoUser);
	}

}
