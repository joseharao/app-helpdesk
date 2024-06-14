package com.inforcentersistemas.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcentersistemas.helpdesk.domains.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
