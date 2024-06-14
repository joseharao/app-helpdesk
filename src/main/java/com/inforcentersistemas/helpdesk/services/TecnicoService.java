package com.inforcentersistemas.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcentersistemas.helpdesk.domains.Tecnico;
import com.inforcentersistemas.helpdesk.repositories.TecnicoRepository;
import com.inforcentersistemas.helpdesk.services.execptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no banco de dados ID: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
}
