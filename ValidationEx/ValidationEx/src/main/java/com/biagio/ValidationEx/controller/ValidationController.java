package com.biagio.ValidationEx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biagio.ValidationEx.model.Actor;
import com.biagio.ValidationEx.model.Movie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cinema")
public class ValidationController {
	
	private Map<Integer,Movie> archive = new HashMap<Integer,Movie>();
	private Map<Integer,Actor> actors = new HashMap<Integer,Actor>();
	private Integer storedIds[] = {1,1}; //First id is films, second is actors
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllFilms(){
		return ResponseEntity.ok(new ArrayList<Movie>(archive.values()));
	}
	@GetMapping("/actors")
		public ResponseEntity<List<Actor>> getAllActors(){
			return ResponseEntity.ok(new ArrayList<Actor>(actors.values()));
	}
	
	@PostMapping("/movies")
	public ResponseEntity<Movie> addMovie(@RequestBody @Valid Movie movie){
		movie.setId(storedIds[0]);
		archive.put(storedIds[0],movie);
		storedIds[0]++;
		return ResponseEntity.ok(movie);
	}
	@PostMapping("/movies")
	public ResponseEntity<Actor> addActor(@RequestBody @Valid Actor actor){
		actor.setId(storedIds[1]);
		actors.put(storedIds[1],actor);
		storedIds[0]++;
		return ResponseEntity.ok(actor);
	}


}
