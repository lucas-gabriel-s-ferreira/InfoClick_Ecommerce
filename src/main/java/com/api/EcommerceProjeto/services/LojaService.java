package com.api.EcommerceProjeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.EcommerceProjeto.domain.Empreendedor;
import com.api.EcommerceProjeto.domain.Loja;
import com.api.EcommerceProjeto.domain.dtos.LojaDTO;
import com.api.EcommerceProjeto.repositories.EmpreendedorRepository;
import com.api.EcommerceProjeto.repositories.LojaRepository;
import com.api.EcommerceProjeto.services.exceptions.ObjectNotFoundException;

@Service
public class LojaService {
	@Autowired
	private EmpreendedorRepository empRepository;
	@Autowired
	private LojaRepository repository;

	public Loja findById(Integer id) {
		Optional<Loja> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Loja não foi encontrada"));
	}

	public List<Loja> findAllLojas() {
		return repository.findAll();
	}

	public Loja findLojaByEmail(String email) {
		Optional<Loja> obj = repository.findLojaByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Loja não encontrada pelo email: " + email));
	}

	public Loja create(LojaDTO objDto, Integer idEmpreendedor) {
		objDto.setIdLoja(idEmpreendedor);
		Optional<Empreendedor> obj = empRepository.findById(idEmpreendedor);
		objDto.setEmpreendedor(obj.get());

		Loja newObj = new Loja(objDto);
		return repository.save(newObj);
	}

	public Loja update(Integer id, LojaDTO objDto) {

		Loja oldObj = findById(id);

		objDto.setIdLoja(oldObj.getIdLoja());
		objDto.setEmpreendedor(oldObj.getEmpreendedor());

		return repository.save(new Loja(objDto));
	}

	public void delete(Integer id) {
		Loja obj = findById(id);
		if(obj.getProdutos().size() > 0){
			throw new DataIntegrityViolationException("Não é possível excluir uma loja que possui produtos");
		}

		repository.deleteById(id);
	}

}
