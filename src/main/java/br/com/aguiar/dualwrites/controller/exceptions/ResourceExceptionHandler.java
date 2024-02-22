package br.com.aguiar.dualwrites.controller.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.aguiar.dualwrites.exceptions.DateIntegratyViolationException;
import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MensagemError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        MensagemError error = new MensagemError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DateIntegratyViolationException.class)
    public ResponseEntity<MensagemError> dateIntegratyViolationException(DateIntegratyViolationException ex,
            HttpServletRequest request) {
        MensagemError error = new MensagemError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<MensagemError> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> new MensagemError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), x.getDefaultMessage(),
                request.getRequestURI())).collect(Collectors.toList());

		return ResponseEntity.badRequest().body(errors);
	}

}
