package com.inforcentersistemas.helpdesk;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inforcentersistemas.helpdesk.domains.Chamado;
import com.inforcentersistemas.helpdesk.domains.Cliente;
import com.inforcentersistemas.helpdesk.domains.Tecnico;
import com.inforcentersistemas.helpdesk.domains.enums.Perfil;
import com.inforcentersistemas.helpdesk.domains.enums.Prioridade;
import com.inforcentersistemas.helpdesk.domains.enums.Status;
import com.inforcentersistemas.helpdesk.repositories.ChamadoRepository;
import com.inforcentersistemas.helpdesk.repositories.ClienteRepository;
import com.inforcentersistemas.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}
    
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	
	public void run(String ... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "JOSE HARAO", "00000000000", "joseharao@icloud.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "705.717.440-13", "torvalds@gmail.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Observacao de chamado 01", tec1, cli1);
	
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
}
