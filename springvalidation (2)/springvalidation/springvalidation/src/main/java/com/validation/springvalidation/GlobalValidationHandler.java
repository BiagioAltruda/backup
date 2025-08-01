/**
 * 
 */
package com.validation.springvalidation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 */
//Annota questa classe come componente globale per gestire le eccezioni di tutti i controller
@RestControllerAdvice
public class GlobalValidationHandler extends ResponseEntityExceptionHandler {

 /**
  * Questo metodo viene automaticamente chiamato quando una richiesta @Valid fallisce.
  * Spring genera una MethodArgumentNotValidException che contiene tutti gli errori di validazione.
  */
 @Override
 protected ResponseEntity<Object> handleMethodArgumentNotValid(
         MethodArgumentNotValidException ex,    // contiene tutti i dettagli degli errori
         HttpHeaders headers,                   // headers HTTP della risposta
         HttpStatusCode status,                 // stato HTTP (es. 400 Bad Request)
         WebRequest request) {                  // la richiesta web che ha causato l’errore

     // Mappa per contenere errori campo-per-campo (es: "name": "è obbligatorio")
     Map<String, String> errors = new HashMap<>();

     // Itera su tutti gli errori nei campi del form/body (non gli oggetti interi)
     ex.getBindingResult().getFieldErrors().forEach(error -> {
         String field = error.getField();              // Nome del campo (es: name)
         String message = error.getDefaultMessage();   // Messaggio di errore definito nell'annotazione
         errors.put(field, message);                   // Inserisce nel dizionario (campo → messaggio)
     });

     // Corpo JSON di risposta personalizzato
     Map<String, Object> body = new LinkedHashMap<>();
     body.put("status", status.value()); // es. 400
     body.put("errors", errors);         // mappa degli errori di validazione

     // Restituisce una ResponseEntity con corpo, stato e intestazioni
     return ResponseEntity.status(status).body(body);
 }
}
