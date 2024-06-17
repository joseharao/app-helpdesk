package com.inforcentersistemas.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inforcentersistemas.helpdesk.domains.Pessoa;
import com.inforcentersistemas.helpdesk.domains.Tecnico;
import com.inforcentersistemas.helpdesk.domains.dtos.TecnicoDTO;
import com.inforcentersistemas.helpdesk.repositories.PessoaRepository;
import com.inforcentersistemas.helpdesk.repositories.TecnicoRepository;
import com.inforcentersistemas.helpdesk.services.execptions.DataIntegrityViolationException;
import com.inforcentersistemas.helpdesk.services.execptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no banco de dados ID: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico Create(TecnicoDTO dto) {
		dto.setId(null);
		dto.setSenha(dto.getSenha());
		validaPorCpfEEmail(dto);
		Tecnico tecnico = new Tecnico(dto);
		return tecnicoRepository.save(tecnico);
	}

	private void validaPorCpfEEmail(TecnicoDTO dto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(dto.getEmail());
		
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO dto) {
		dto.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(dto);
		oldObj = new Tecnico(dto);
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser excluído!");
		}
		tecnicoRepository.deleteById(id);
	}
}
