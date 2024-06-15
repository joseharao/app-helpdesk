package com.inforcentersistemas.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inforcentersistemas.helpdesk.domains.Cliente;
import com.inforcentersistemas.helpdesk.domains.dtos.ClienteDTO;
import com.inforcentersistemas.helpdesk.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		Cliente obj = this.clienteService.findById(id);
		return ResponseEntity.ok(new ClienteDTO(obj));
	}
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
		Cliente cliente = clienteService.Create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto){
		Cliente obj = clienteService.update(id, dto);
		return ResponseEntity.ok(new ClienteDTO(obj));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
