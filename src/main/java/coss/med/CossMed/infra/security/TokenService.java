package coss.med.CossMed.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import coss.med.CossMed.domain.user.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Coss.Med")
					.withSubject(user.getLogin())
					.withExpiresAt(expirationDate())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error generation JWT token", exception);
		}
	}

	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
