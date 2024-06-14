package com.inforcentersistemas.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.inforcentersistemas.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dbService;

    @Bean
    Boolean instanciaDB() {
		this.dbService.instanciaDB();
		return true;
	}
}
