package com.salary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salary.model.EmployeeModel;
import com.salary.model.dtos.EmployeeInsertDTO;
import com.salary.model.dtos.EmployeeResponseDTO;
import com.salary.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário", description = "End-Point de Funcionário")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	@Operation(summary = "Lista geral de Funcionário", description = "Função responsável por lista todos os funcionários")
	public ResponseEntity<List<EmployeeResponseDTO>> findAllEmployee() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAllEMployee());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista Funcionário por id", description = "Função responsável por lista funcionário por id")
	public ResponseEntity<EmployeeResponseDTO> getOneEmployee(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getOneEmployee(id));
	}

	@PostMapping
	@Operation(summary = "Cria funcionário", description = "Função para criar o cadastro de funcionário")
	public ResponseEntity<EmployeeResponseDTO> createEmployee(EmployeeInsertDTO employeeInsertDTO) {
		EmployeeModel employeeModel = employeeService.createEmployee(employeeInsertDTO);
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(employeeModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDTO);
	}
}
