package com.inforcentersistemas.helpdesk.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inforcentersistemas.helpdesk.domains.Pessoa;
import com.inforcentersistemas.helpdesk.repositories.PessoaRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//define como componente da aplicação para ser usado como autoried em outras classes herdando de OncePerRequestFilter
@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;
	@Autowired
	PessoaRepository pessoaRepository;

	// metodo para adicionar o um de validação do acesso de usuario antes de entrar
	// nos filtros de seguranças padrões da classe ConfigSecurity
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//pega o token da requisição enviada
		var token = this.restaurarToken(request);
		//verifica se existe o token
		if (token != null) {
			//Valida o token recebido
			var login = tokenService.validarToken(token);
			//retorna o usuario loago pelo usuario do token do getUserName do token
			Optional<Pessoa> p = pessoaRepository.findByEmail(login);
			//Criar um usuario JWT com os dados do usuario autorizado para ser adicionado a sua a autorização de acesso
			UsuarioDetails user = new UsuarioDetails(p.get().getId(), p.get().getEmail(), p.get().getSenha(),
					p.get().getPerfis());
			//gerar os tokens validados
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			//adiciona a autorização de acesso do usuario ao filtro padrao do spring
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		//caso nao esteja autenticado segue o fluxo para o filtro padrao definido na classe ConfigSecurity
		filterChain.doFilter(request, response);
	}
	//metodo para restaurar o token do cabeçalho da requisição do usuário
	private String restaurarToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null)
			return null;
		return authHeader.replace("Bearer ", "");
	}
}
