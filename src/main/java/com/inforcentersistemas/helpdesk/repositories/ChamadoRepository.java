package com.inforcentersistemas.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcentersistemas.helpdesk.domains.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
