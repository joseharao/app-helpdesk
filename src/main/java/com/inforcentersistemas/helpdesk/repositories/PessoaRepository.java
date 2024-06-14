package com.inforcentersistemas.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcentersistemas.helpdesk.domains.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
