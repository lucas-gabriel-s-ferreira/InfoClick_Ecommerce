package com.api.EcommerceProjeto.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.EcommerceProjeto.domain.Produto;
import com.api.EcommerceProjeto.domain.dtos.ProdutoDTO;
import com.api.EcommerceProjeto.repositories.ProdutoRepository;
import com.api.EcommerceProjeto.services.ProdutoService;

@RestController
@RequestMapping(value = "/service/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	private ProdutoRepository repository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAllProduto() {
		List<Produto> list = service.findAllProduto();
		List<ProdutoDTO> listDto = list.stream().map(prod -> new ProdutoDTO(prod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}



	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@PostMapping(value = "/{id_loja}")
	public ResponseEntity<ProdutoDTO> createProduto(@Valid @PathVariable Integer id_loja, @RequestBody ProdutoDTO objDto) {
		Produto newObj = service.create(objDto, id_loja);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@PutMapping(value = "/{idProduto}")
	public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Integer idProduto, @RequestBody ProdutoDTO objDto) {
		Produto obj = service.update(idProduto, objDto);
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPREENDEDOR', 'ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/buscarPorCategoria")
	@ResponseBody
	public ResponseEntity<List<Produto>> buscarPorCategoria(@RequestParam (name =  "categoria") String categoria) {
		
		List<Produto> produto = repository.buscarPorCategoria(categoria);
       
        return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
    }

	@GetMapping(value = "/buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam (name = "name") String name) {

		List<Produto> produto = repository.buscarPorNome(name.trim().toLowerCase());

		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
	}


}
