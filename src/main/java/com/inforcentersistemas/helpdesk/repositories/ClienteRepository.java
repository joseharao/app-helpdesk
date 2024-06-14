package com.inforcentersistemas.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcentersistemas.helpdesk.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
