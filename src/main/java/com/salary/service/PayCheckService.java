package com.salary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salary.exceptions.ResourceAlreadyRegisteredException;
import com.salary.exceptions.ResourceNotFoundException;
import com.salary.model.EmployeeModel;
import com.salary.model.PayCheckModel;
import com.salary.model.dtos.EmployeeResponseDTO;
import com.salary.model.dtos.PayCheckInsertDTO;
import com.salary.model.dtos.PayCheckResponseDTO;
import com.salary.repository.PaycheckRepository;

@Service
public class PayCheckService {

	private PaycheckRepository paycheckRepository;
	private EmployeeService employeeService;

	public PayCheckService(PaycheckRepository paycheckRepository, EmployeeService employeeService) {
		this.paycheckRepository = paycheckRepository;
		this.employeeService = employeeService;
	}

	@Transactional(readOnly = true)
	public List<PayCheckResponseDTO> findAllPayCheck() {
		List<PayCheckModel> listPayCheck = paycheckRepository.findAll();
		List<PayCheckResponseDTO> lisPayCheckResponseDTO = new ArrayList<>();

		for (PayCheckModel payCheckModel : listPayCheck) {
			PayCheckResponseDTO payCheckResponseDTO = new PayCheckResponseDTO(payCheckModel);
			lisPayCheckResponseDTO.add(payCheckResponseDTO);
		}

		return lisPayCheckResponseDTO;
	}

	@Transactional(readOnly = true)
	public PayCheckResponseDTO getOnePayCheck(long id) {
		PayCheckModel payCheckModel = paycheckRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("ContraCheque de id %s não encontrado!".formatted(id)));
		PayCheckResponseDTO payCheckResponseDTO = new PayCheckResponseDTO(payCheckModel);
		return payCheckResponseDTO;
	}

	@Transactional
	public PayCheckModel createPayCheck(PayCheckInsertDTO payCheckInsertDTO) {
		PayCheckModel payCheckModel = new PayCheckModel();
		EmployeeModel employeeModel = new EmployeeModel();
		
		if (this.existsByEmployee(payCheckInsertDTO.employee_id())) {
			throw new ResourceAlreadyRegisteredException(
					"Funcionário de ID: %s não encontrado".formatted(payCheckInsertDTO.employee_id()));
		} else {
			EmployeeResponseDTO employeeResponseDTO = this.employeeService.getOneEmployee(payCheckInsertDTO.employee_id());
			
			BeanUtils.copyProperties(employeeResponseDTO, employeeModel);
		}
		payCheckModel.setEmployee(employeeModel);

		payCheckModel.setNetSalary(this.payChekEmployee(payCheckModel.getEmployee().getGrossSalary()));

		return paycheckRepository.save(payCheckModel);

	}

	public double payChekEmployee(double salary) {

		double dicountEmployee = this.discount(salary);

		double taxEmployee = this.taxService(salary);

		return salary - dicountEmployee - taxEmployee;

	}

	public double taxService(double salaryTax) {
		return salaryTax * 0.2;
	}

	public double discount(double salaryPensiona) {
		return salaryPensiona * 0.1;
	}

	private boolean existsByEmployee(Long employee_id) {
		EmployeeResponseDTO employeeResponseDTO = this.employeeService.getOneEmployee(employee_id);
		EmployeeModel employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employeeResponseDTO, employeeModel);

		return this.paycheckRepository.existsByEmployee(employeeModel);
	}
}
