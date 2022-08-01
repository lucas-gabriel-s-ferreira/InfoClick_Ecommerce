package com.api.EcommerceProjeto.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.EcommerceProjeto.domain.Loja;
import com.api.EcommerceProjeto.domain.dtos.LojaDTO;
import com.api.EcommerceProjeto.services.LojaService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/service/lojas")
public class LojaController {
	@Autowired
	private LojaService service;

	@GetMapping(value = "find/{id}")
	public ResponseEntity<LojaDTO> findById(@PathVariable Integer id) {
		Loja obj = this.service.findById(id);
		return ResponseEntity.ok().body(new LojaDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<LojaDTO>> findAll() {
		List<Loja> list = service.findAllLojas();
		List<LojaDTO> listDTO = list.stream().map(obj -> new LojaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{email}")
	public ResponseEntity<LojaDTO> findLojaByEmail(@PathVariable String email) {
		Loja obj = this.service.findLojaByEmail(email);
		return ResponseEntity.ok().body(new LojaDTO(obj));
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@PostMapping("/{idEmpreendedor}")
	public ResponseEntity<LojaDTO> create(@PathVariable Integer idEmpreendedor, @RequestBody LojaDTO objDTO) {
		Loja newObj = service.create(objDTO, idEmpreendedor);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/id").buildAndExpand(newObj.getIdLoja())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@PutMapping(value = "/{idLoja}")
	public ResponseEntity<LojaDTO> updateLoja(@PathVariable Integer idLoja, @RequestBody LojaDTO objDto) {
		Loja obj = service.update(idLoja, objDto);
		return ResponseEntity.ok().body(new LojaDTO(obj));
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LojaDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}