package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.domain.user.AuthenticationDataDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthenticatorController {

	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO body) {
		var token = new UsernamePasswordAuthenticationToken(body.login(), body.password());
		var authentication = manager.authenticate(token);
		
		return ResponseEntity.ok().build();
	}
	
}
