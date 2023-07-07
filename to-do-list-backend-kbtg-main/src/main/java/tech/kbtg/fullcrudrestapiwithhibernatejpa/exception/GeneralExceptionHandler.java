package tech.kbtg.fullcrudrestapiwithhibernatejpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<GeneralExceptionResponse> generalException(Exception ex) {
		GeneralExceptionResponse generalExceptionResponse = new GeneralExceptionResponse();
		
		generalExceptionResponse.setMessage( ex.getMessage() );
		generalExceptionResponse.setStatusCode( HttpStatus.BAD_REQUEST.value() );

		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( generalExceptionResponse );
	}
	
	@ExceptionHandler
	public ResponseEntity<GeneralExceptionResponse> notFoundException(NotFoundResourceException ex) {
		GeneralExceptionResponse generalExceptionResponse = new GeneralExceptionResponse();

		generalExceptionResponse.setMessage( ex.getMessage() );
		generalExceptionResponse.setStatusCode( HttpStatus.NOT_FOUND.value() );

		return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( generalExceptionResponse );
	}
}
