package com.inforcentersistemas.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
