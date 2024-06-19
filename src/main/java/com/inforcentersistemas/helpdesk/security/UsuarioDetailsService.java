package com.inforcentersistemas.helpdesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inforcentersistemas.helpdesk.domains.Pessoa;
import com.inforcentersistemas.helpdesk.repositories.PessoaRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoa;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa p = pessoa.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário Não Encontrado")) ;
		return new UsuarioDetails(p.getId(), p.getEmail(), p.getSenha(), p.getPerfis());
	}


}
