package com.biagio.CRUDex.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biagio.CRUDex.model.Libro;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/libreria")
@CrossOrigin(origins="*")
public class LibreriaCtr {
	
	private int counter = 1;
	private Map<Integer, Libro> archivio = new HashMap<>();
	
	@GetMapping
	public List<Libro> getAllLibri(){
		return new ArrayList<Libro>(archivio.values()); //Restituisce una lista con tutti i libri
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Libro> getLibro (@PathVariable int id) { //richiedo l'id del libro
		if(archivio.containsKey(id)) //se è presente
			return ResponseEntity.ok(archivio.get(id)); //lo restituisco al browser
		else {
			return ResponseEntity.notFound().build(); //altrimenti rimando un errore
		}
	}
	
	@GetMapping("/autore/{autore}")
	public ResponseEntity<List<Libro>> getLibroByAutore(@PathVariable String autore){ //ricerca per autore	
		List<Libro> entries = new ArrayList<>(); //Creo lista che contiene i match
		for (Libro l: archivio.values()) //controllo l'archivio per dei potenziali match
			if(l.getAuthor().equalsIgnoreCase(autore)) //controllo nome autore
				entries.add(l); //aggiungo il libro alla lista
		if(entries.size()>0) // se la lista è non vuota
			return ResponseEntity.ok(entries); //restituisco i libri
		return ResponseEntity.notFound().build(); 
	}
	
	@PostMapping
	public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){ //RequestBody per oggetti complessi
		int id = counter++; //incremento contatore
		libro.setId(id);
		archivio.put(id, libro);
		return ResponseEntity.ok(libro); 
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateLibro(@RequestBody @Valid Libro libro, @PathVariable int id) { //jakarta Validation
		if(!archivio.containsKey(id))
			return ResponseEntity.notFound().build();
		archivio.put(id, libro);
		return ResponseEntity.ok("Libro aggiornato con successo");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLibro (@PathVariable int id){
		if(archivio.remove(id)==null)
			return ResponseEntity.ok("Rimozione effettuata con successo");
		return ResponseEntity.noContent().build();
	}
}
