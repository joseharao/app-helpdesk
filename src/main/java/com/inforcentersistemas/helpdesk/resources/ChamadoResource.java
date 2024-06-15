package com.inforcentersistemas.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcentersistemas.helpdesk.domains.Chamado;
import com.inforcentersistemas.helpdesk.domains.dtos.ChamadoDTO;
import com.inforcentersistemas.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value="/api/chamados")
public class ChamadoResource {
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok(new ChamadoDTO(obj));
	}
}
