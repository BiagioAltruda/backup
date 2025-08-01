/**
 * 
 */
package com.validation.springvalidation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.validation.springvalidation.model.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    // POST /products -> riceve un oggetto JSON e lo valida automaticamente
	/* 
	 *  Cos'è @Valid?
		Dice a Spring di validare automaticamente il corpo della richiesta (@RequestBody) prima di eseguire il metodo.
		Se ci sono errori, Spring restituirà automaticamente un errore HTTP 400 con i dettagli
	 * */
    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        // Se la validazione va a buon fine, si arriva qui
        return ResponseEntity.ok("Prodotto creato con successo");
    }
}
