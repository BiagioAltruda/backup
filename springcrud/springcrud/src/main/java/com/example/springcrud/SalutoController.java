/**
 * 
 */
package com.example.springcrud;

/**
 * 
 */
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalutoController {

    /**
     * Questo metodo gestisce sia GET che POST sulla stessa rotta.
     * Accetta 3 parametri:
     * - nome (obbligatorio)
     * - lingua (opzionale, default "it")
     * - formale (opzionale, default false)
     */
    @RequestMapping(value = "/saluta", method = {RequestMethod.GET, RequestMethod.POST})
    public String saluta(
            @RequestParam String nome,                                   // obbligatorio
            @RequestParam(defaultValue = "it") String lingua,             // opzionale con default
            @RequestParam(defaultValue = "false") boolean formale         // opzionale con default
    ) {
        String saluto;

        // Logica multilingua semplice
        switch (lingua.toLowerCase()) {
            case "en":
                saluto = formale ? "Good day, " : "Hi, ";
                break;
            case "fr":
                saluto = formale ? "Bonjour, " : "Salut, ";
                break;
            default:
                saluto = formale ? "Buongiorno, " : "Ciao, ";
        }

        return saluto + nome + "!";
    }
}

