package com.inforcentersistemas.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcentersistemas.helpdesk.domains.Pessoa;
import com.inforcentersistemas.helpdesk.security.UsuarioDetails;


public interface UsuarioRepository extends JpaRepository<Pessoa, Integer> {
	
	UsuarioDetails findByEmail(String email);
}
