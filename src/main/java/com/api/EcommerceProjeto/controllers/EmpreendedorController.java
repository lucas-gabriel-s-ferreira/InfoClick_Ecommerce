package com.api.EcommerceProjeto.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.api.EcommerceProjeto.domain.Empreendedor;
import com.api.EcommerceProjeto.domain.dtos.EmpreendedorDTO;
import com.api.EcommerceProjeto.services.EmpreendedorService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "service/empreendedores")
public class EmpreendedorController {
	@Autowired
	private EmpreendedorService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmpreendedorDTO> findById(@PathVariable Integer id) {
		Empreendedor obj = this.service.findById(id);
		return ResponseEntity.ok().body(new EmpreendedorDTO(obj));
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Integer> findIdByEmail(@PathVariable String email) {
		Integer obj = this.service.findIdByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<EmpreendedorDTO>> findAll() {
		List<Empreendedor> list = service.findAll();
		List<EmpreendedorDTO> listDTO = list.stream().map(obj -> new EmpreendedorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<EmpreendedorDTO> create(@Valid @RequestBody EmpreendedorDTO objDTO) {
		Empreendedor newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/id").buildAndExpand(newObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmpreendedorDTO> updateEmpreendedor(@PathVariable Integer id,
			@RequestBody EmpreendedorDTO objDto) {
		Empreendedor obj = service.update(id, objDto);
		return ResponseEntity.ok().body(new EmpreendedorDTO(obj));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPREENDEDOR')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EmpreendedorDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}