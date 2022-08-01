package com.api.EcommerceProjeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.EcommerceProjeto.domain.Empreendedor;
import com.api.EcommerceProjeto.domain.dtos.EmpreendedorDTO;
import com.api.EcommerceProjeto.repositories.EmpreendedorRepository;

@Service
public class EmpreendedorService {

	@Autowired
	private EmpreendedorRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Empreendedor findById(Integer id) {
		Optional<Empreendedor> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public Integer findIdByEmail(String email) {
		return repository.findIdByEmail(email);
	}

	public List<Empreendedor> findAll() {
		return repository.findAll();
	}

	public Empreendedor create(EmpreendedorDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validaEmailECnpj(objDTO);
		Empreendedor newObj = new Empreendedor(objDTO);
		return repository.save(newObj);
	}

	public Empreendedor update(Integer id, EmpreendedorDTO objDto) {
		objDto.setId(id);
		objDto.setPassword(encoder.encode(objDto.getPassword()));
		Empreendedor oldObj = findById(id);
		validaEmailECnpj(objDto);
		oldObj = new Empreendedor(objDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Empreendedor obj = findById(id);
		if(obj.getLoja() != null){
			throw new DataIntegrityViolationException("Não é possível excluir um empreendedor que possui loja");
		}
		repository.deleteById(id);

	}

	private void validaEmailECnpj(EmpreendedorDTO objDTO) {
		Optional<Empreendedor> obj = repository.findByEmail(objDTO.getCnpj());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado!");
		}
		obj = repository.findByCnpj(objDTO.getCnpj());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CNPJ já cadastrado!");
		}
	}
}