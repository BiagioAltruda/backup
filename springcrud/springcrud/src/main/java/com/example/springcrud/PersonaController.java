/**
 * 
 */
package com.example.springcrud;

/**
 * 
 */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/persone")
public class PersonaController {

    // Simuliamo un archivio in memoria
    private final Map<Integer, Persona> archivio = new HashMap<>();
    private int idCounter = 1;

    // --- GET: recupera tutte le persone ---
    @GetMapping
    public List<Persona> getAll() {
        return new ArrayList<>(archivio.values());
    }

    // --- POST: aggiunge una nuova persona ---
    @PostMapping
    public ResponseEntity<String> addPersona(@RequestBody Persona persona) {
        int id = idCounter++;
        persona.setId(id);
        archivio.put(id, persona);
        return ResponseEntity
                .ok("Persona aggiunta con ID: " + id);
    }

    // --- PUT: aggiorna persona esistente ---
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersona(@PathVariable int id, @RequestBody Persona nuovaPersona) {
        if (!archivio.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        nuovaPersona.setId(id); // preserva l'ID originale
        archivio.put(id, nuovaPersona);
        return ResponseEntity.ok("Persona con ID " + id + " aggiornata.");
    }

    // --- DELETE: rimuove persona ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable int id) {
        if (archivio.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Persona con ID " + id + " eliminata.");
    }

    // Classe interna per rappresentare la risorsa Persona
    static class Persona {
        private int id;
        private String nome;
        private int eta;

        public Persona() {}

        public Persona(int id, String nome, int eta) {
            this.id = id;
            this.nome = nome;
            this.eta = eta;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public int getEta() { return eta; }
        public void setEta(int eta) { this.eta = eta; }
    }
}
