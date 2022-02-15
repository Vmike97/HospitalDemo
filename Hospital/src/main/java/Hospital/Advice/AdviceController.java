package Hospital.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import Hospital.ErrorHandling.MyResourceNotFoundException;

@ControllerAdvice
public class AdviceController {
	@ExceptionHandler(MyResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(MyResourceNotFoundException nf){
		return new ResponseEntity<String>("Id does not exist", HttpStatus.BAD_REQUEST);
	}
}
