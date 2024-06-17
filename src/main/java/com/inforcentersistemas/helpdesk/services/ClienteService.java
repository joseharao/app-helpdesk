package com.inforcentersistemas.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inforcentersistemas.helpdesk.domains.Pessoa;
import com.inforcentersistemas.helpdesk.domains.Cliente;
import com.inforcentersistemas.helpdesk.domains.dtos.ClienteDTO;
import com.inforcentersistemas.helpdesk.repositories.PessoaRepository;
import com.inforcentersistemas.helpdesk.repositories.ClienteRepository;
import com.inforcentersistemas.helpdesk.services.execptions.DataIntegrityViolationException;
import com.inforcentersistemas.helpdesk.services.execptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado no banco de dados ID: " + id));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente Create(ClienteDTO dto) {
		dto.setId(null);
		dto.setSenha(dto.getSenha());
		validaPorCpfEEmail(dto);
		Cliente tecnico = new Cliente(dto);
		return tecnicoRepository.save(tecnico);
	}

	private void validaPorCpfEEmail(ClienteDTO dto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(dto.getEmail());
		
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}

	public Cliente update(Integer id, @Valid ClienteDTO dto) {
		dto.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(dto);
		oldObj = new Cliente(dto);
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser excluído!");
		}
		tecnicoRepository.deleteById(id);
	}
}
