package com.example.SpringCookieSimple.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    // Mappa che simula un database di utenti (username -> password)
    private Map<String, String> utenti = new HashMap<>();

    // Set che contiene i token attivi di sessione.
    // Usiamo Set perché ci interessa solo sapere se un token esiste, non associare valori.
    // A differenza di Map, un Set memorizza solo elementi unici (senza chiave-valore),
    // ideale per verificare rapidamente la presenza di un token.
    private Set<String> sessioniValide = new HashSet<>();

    // Costruttore: aggiunge due utenti "hardcoded"
    public UserController() {
        utenti.put("alice", "1234");
        utenti.put("bob", "abcd");
    }

    // Classe statica usata per rappresentare il corpo JSON della richiesta di login
    public static class LoginRequest {
        public String username; // sarà mappato dal campo "username" del JSON
        public String password; // sarà mappato dal campo "password" del JSON
    }

    /**
     * Endpoint POST per effettuare il login.
     * Riceve un oggetto JSON con username e password,
     * controlla se sono validi, e restituisce un token casuale se ok.
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        String username = request.username;
        String password = request.password;

        // Verifica se l'utente esiste e la password corrisponde (da sostituire con chiamate al database!)
        if (utenti.containsKey(username) && utenti.get(username).equals(password)) {
            // Genera un token univoco per l'utente
            String token = UUID.randomUUID().toString();
            // Aggiunge il token alle sessioni attive
            sessioniValide.add(token);
            // Restituisce il token al client
            return token;
        }

        // Login fallito
        return "LOGIN_FAILED";
    }

    /**
     * Endpoint GET per accedere al profilo dell'utente.
     * Richiede un header personalizzato "X-Token" che identifica la sessione.
     */
    @GetMapping("/profilo")
    public String profilo(@RequestHeader(value = "X-Token", required = false) String token) {
        // Controlla se il token è presente e valido
        if (token != null && sessioniValide.contains(token)) {
            // Accesso autorizzato
            return "Accesso con token valido: " + token;
        }

        // Token mancante o non valido
        return "ACCESSO NEGATO";
    }

    /**
     * Endpoint POST per il logout dell'utente.
     * Rimuove il token dal set delle sessioni valide.
     */
    @PostMapping("/logout")
    public String logout(@RequestHeader(value = "X-Token", required = false) String token) {
        // Rimuove il token, se presente
        sessioniValide.remove(token);
        // Conferma logout
        return "Logout ok";
    }
}