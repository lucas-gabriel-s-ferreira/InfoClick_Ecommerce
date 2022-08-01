package com.api.EcommerceProjeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.EcommerceProjeto.domain.Consumidor;
import com.api.EcommerceProjeto.domain.dtos.ConsumidorDTO;
import com.api.EcommerceProjeto.repositories.ConsumidorRepository;
import com.api.EcommerceProjeto.services.exceptions.ObjectNotFoundException;

@Service
public class ConsumidorService {

	@Autowired
	private ConsumidorRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<Consumidor> findAllConsumidor() {
		return repository.findAll();
	}

	public Consumidor findById(Integer id) {
		Optional<Consumidor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + id));
	}

	public Consumidor create(ConsumidorDTO objDto) {
		objDto.setId(null);
		objDto.setPassword(encoder.encode(objDto.getPassword()));
		validaEmailECpf(objDto);
		Consumidor newObj = new Consumidor(objDto);
		return repository.save(newObj);

	}

	public Consumidor update(Integer id, ConsumidorDTO objDto) {
		objDto.setId(id);
		objDto.setPassword(encoder.encode(objDto.getPassword()));
		Consumidor oldObj = findById(id);
		validaEmailECpf(objDto);
		oldObj = new Consumidor(objDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Consumidor obj = findById(id);
		repository.deleteById(id);
	}

	private void validaEmailECpf(ConsumidorDTO objDTO) {
		Optional<Consumidor> obj = repository.findByEmail(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado!");
		}
		obj = repository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}
	}

	public Integer findIdByEmail(String email){
		return repository.findIdByEmail(email);
	}
}