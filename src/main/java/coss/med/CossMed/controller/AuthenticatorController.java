package coss.med.CossMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coss.med.CossMed.domain.user.AuthenticationDataDTO;
import coss.med.CossMed.domain.user.User;
import coss.med.CossMed.domain.user.UserDetailsDTO;
import coss.med.CossMed.domain.user.UserRepository;
import coss.med.CossMed.infra.security.JWTTokenDataDTO;
import coss.med.CossMed.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthenticatorController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping
	public ResponseEntity<JWTTokenDataDTO> login(@RequestBody @Valid AuthenticationDataDTO body) {
		var authToken = new UsernamePasswordAuthenticationToken(body.login(), body.password());
		var authentication = manager.authenticate(authToken);

		var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
		
		return ResponseEntity.ok(new JWTTokenDataDTO(tokenJWT));
	}
	
	@Transactional
	@PostMapping("/signup")
	public ResponseEntity<UserDetailsDTO> createUser(@RequestBody @Valid AuthenticationDataDTO body) {
		var user = new User(body);
		
		user.setPassword(encoder.encode(body.password()));
		
		System.out.println(body.password());
		
		//System.out.println(new_pass);
		
		repository.save(user);
		
		return ResponseEntity.ok(new UserDetailsDTO(user));
	}
}
