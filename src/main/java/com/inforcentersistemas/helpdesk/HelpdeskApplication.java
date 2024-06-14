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
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "705.717.440-11", "torvalds@gmail.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Observacao de chamado 01", tec1, cli1);

		Tecnico tec2 = new Tecnico(null, "ANTONIO DA SILVA", "00000000001", "antoniodasilva@icloud.com", "123");
		tec2.addPerfil(Perfil.ADMIN);
		
		Cliente cli2 = new Cliente(null, "FRANCISCO", "705.717.440-12", "francisco@gmail.com", "123");
		cli2.addPerfil(Perfil.CLIENTE);
		
		Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "Observacao de chamado 02", tec2, cli2);

		Tecnico tec3 = new Tecnico(null, "MARIO", "00000000022", "mario@icloud.com", "123");
		tec3.addPerfil(Perfil.ADMIN);
		
		Cliente cli3 = new Cliente(null, "JOSE IVAN", "705.717.440-13", "ivan@gmail.com", "123");
		cli3.addPerfil(Perfil.CLIENTE);
		
		Chamado c3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 03", "Observacao de chamado 03", tec3, cli3);

		
		Tecnico tec4 = new Tecnico(null, "CRISTIANO", "00000000023", "cristiano@icloud.com", "123");
		tec4.addPerfil(Perfil.ADMIN);
		
		Cliente cli4 = new Cliente(null, "Erisvaldo", "705.717.440-16", "erisvaldo@gmail.com", "123");
		cli4.addPerfil(Perfil.CLIENTE);
		
		Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 04", "Observacao de chamado 04", tec4, cli4);

		
		
		Tecnico tec5 = new Tecnico(null, "Marcos", "00000000035", "marcos@icloud.com", "123");
		tec5.addPerfil(Perfil.ADMIN);
		
		Cliente cli5 = new Cliente(null, "Joao Paulo", "705.717.440-15", "joaopaulo@gmail.com", "123");
		cli5.addPerfil(Perfil.CLIENTE);
		
		Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 05", "Observacao de chamado 05", tec5, cli5);

	
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		
	}
}
