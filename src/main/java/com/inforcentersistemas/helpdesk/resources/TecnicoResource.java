package com.inforcentersistemas.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inforcentersistemas.helpdesk.domains.Tecnico;
import com.inforcentersistemas.helpdesk.domains.dtos.TecnicoDTO;
import com.inforcentersistemas.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "api/tecnicos")
public class TecnicoResource {
	@Autowired
	private TecnicoService tecnicoService;
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = this.tecnicoService.findById(id);
		return ResponseEntity.ok(new TecnicoDTO(obj));
	}
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO dto){
		Tecnico tecnico = tecnicoService.Create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
