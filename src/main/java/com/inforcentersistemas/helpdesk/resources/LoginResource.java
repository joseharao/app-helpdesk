package com.inforcentersistemas.helpdesk.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcentersistemas.helpdesk.security.TokenService;
import com.inforcentersistemas.helpdesk.security.UsuarioDTO;
import com.inforcentersistemas.helpdesk.security.UsuarioDetails;

@RestController
@RequestMapping("/api/auth")
public class LoginResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	  
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody UsuarioDTO usuario) {
		var usernamepassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha());
		var auth = this.authenticationManager.authenticate(usernamepassword);
		
		var token = tokenService.gerarToken((UsuarioDetails) auth.getPrincipal());
		HttpHeaders headers = new HttpHeaders();
		headers.add("access-control-expose-headers", "Authorization");
		headers.add("Authorization", "Bearer " + token);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
}
