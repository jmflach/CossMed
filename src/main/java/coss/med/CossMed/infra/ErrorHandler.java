package coss.med.CossMed.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handle404Error() {
		return ResponseEntity.notFound().build();
	}
}
