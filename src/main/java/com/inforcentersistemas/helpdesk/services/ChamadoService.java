package com.inforcentersistemas.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcentersistemas.helpdesk.domains.Chamado;
import com.inforcentersistemas.helpdesk.domains.Cliente;
import com.inforcentersistemas.helpdesk.domains.Tecnico;
import com.inforcentersistemas.helpdesk.domains.dtos.ChamadoDTO;
import com.inforcentersistemas.helpdesk.domains.enums.Prioridade;
import com.inforcentersistemas.helpdesk.domains.enums.Status;
import com.inforcentersistemas.helpdesk.repositories.ChamadoRepository;
import com.inforcentersistemas.helpdesk.services.execptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<Chamado> finAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO dto) {
		return chamadoRepository.save(validaChamado(dto));
	}
	
	public Chamado validaChamado(ChamadoDTO dto) {
		Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
		Cliente cliente = clienteService.findById(dto.getCliente());
		Chamado chamado = new Chamado();
		if (dto.getId() != null) {
			chamado.setId(dto.getId());
		}
		
		if (dto.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		chamado.setStatus(Status.toEnum(dto.getStatus()));
		chamado.setTitulo(dto.getTitulo());
		chamado.setObservacoes(dto.getObservacoes());
		return chamado;
	}

	public Chamado update(Integer id, @Valid ChamadoDTO dto) {
		Chamado oldObj = this.findById(id);
		oldObj = this.validaChamado(dto);
		return chamadoRepository.save(oldObj);
	}
}
