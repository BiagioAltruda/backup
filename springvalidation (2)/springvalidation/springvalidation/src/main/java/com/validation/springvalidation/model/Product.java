/**
 * 
 */
package com.validation.springvalidation.model;

/**
 * 
 */
import jakarta.validation.constraints.*;

public class Product {

    @NotNull(message = "L'ID del prodotto è obbligatorio")
    @Positive(message = "L'ID deve essere un numero positivo")
    private Long id;

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    @Size(min = 3, max = 100, message = "Il nome deve essere tra 3 e 100 caratteri")
    private String name;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "Il prezzo deve essere almeno 0.01")
    @Digits(integer = 6, fraction = 2, message = "Il prezzo deve avere massimo 2 decimali")
    private Double price;

    @Min(value = 0, message = "La quantità non può essere negativa")
    @Max(value = 10000, message = "La quantità non può superare 10.000")
    private int quantity;

    @Size(max = 255, message = "La descrizione non può superare 255 caratteri")
    private String description;

    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "Il codice deve seguire il formato AAA-123")
    private String sku; // esempio di codice prodotto formattato

    @AssertTrue(message = "Il prodotto deve essere attivo per poterlo inserire")
    private boolean active;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
