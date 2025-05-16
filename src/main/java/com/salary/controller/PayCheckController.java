package com.salary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salary.model.PayCheckModel;
import com.salary.model.dtos.PayCheckInsertDTO;
import com.salary.model.dtos.PayCheckResponseDTO;
import com.salary.service.PayCheckService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping
@Tag(name = "ContraCheque", description = "End-Point de Contracheque")
public class PayCheckController {

	private PayCheckService payCheckService;
	
	public PayCheckController(PayCheckService payCheckService) {
		this.payCheckService = payCheckService;
	}
	
	@GetMapping
	@Operation(summary = "Lista geral de contracheque por funcionário", description = "Função responsável por lista todos os contracheques por funcionários")
	public ResponseEntity<List<PayCheckResponseDTO>> findAllPayCheck(){
		return ResponseEntity.status(HttpStatus.OK).body(payCheckService.findAllPayCheck());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista geral de contracheque por id", description = "Função responsável por mostrar o contracheques por id")
	public ResponseEntity<PayCheckResponseDTO> getOnePayCheck(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(payCheckService.getOnePayCheck(id));
	}
	
	@PostMapping
	@Operation(summary = "Cria Contracheque de funcionário", description = "Função para criar o cadastro do contracheque do funcionário")
	public ResponseEntity<PayCheckResponseDTO> createEmployee(PayCheckInsertDTO payCheckInsertDTO) {
		PayCheckModel payCheckModel = payCheckService.createPayCheck(payCheckInsertDTO);
		PayCheckResponseDTO payCheckResponseDTO = new PayCheckResponseDTO(payCheckModel);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(payCheckResponseDTO);
	}
}
