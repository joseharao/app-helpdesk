package com.inforcentersistemas.helpdesk.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Integer minutos;

	public String gerarToken(UsuarioDetails user) {
		try {
			//Define o algoritmo de criptografia usado
			Algorithm algorithm = Algorithm.HMAC512(secret);
			//gera o token a partir do usuario autenticado anteriormente e retorna para o usuario
			String token = JWT.create().withIssuer("api-helpdesk").withSubject(user.getEmail())
					.withExpiresAt(gerarExpiracaoToken()).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token de acesso", e);
		}
	}
	//metodo para validar o token enviado na requisição
	public String validarToken(String token) {
		try {
			//define o algoritmo de criptiografia usado
			Algorithm algorithm = Algorithm.HMAC512(secret);
			//retorna o usuario reuisitor do acesso que esta salvo no token para fazer isso sera necessario 
			//reconstruir o token e comprar com e eviado na requisição e em seguida pegar o usuario
			return JWT.require(algorithm).withIssuer("api-helpdesk").build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			//return "";
			throw new JWTVerificationException(exception.getMessage());
		}
	}

	// Metodo para gerar tempo de expiracao do token de acesso
	private Instant gerarExpiracaoToken() {
		return LocalDateTime.now().plusMinutes(this.minutos).toInstant(ZoneOffset.of("-03:00"));
	}
}
