package coss.med.CossMed.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import coss.med.CossMed.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var tokenJWT = retrieveToken(request);
		
		System.out.println(tokenJWT);

		if (tokenJWT != null) {
			System.out.println("logado");
			var subject = tokenService.getSubject(tokenJWT);
			var user = userRepository.findByLogin(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); 
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println("logado");
		}

		filterChain.doFilter(request, response);
	}

	private String retrieveToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}

}
