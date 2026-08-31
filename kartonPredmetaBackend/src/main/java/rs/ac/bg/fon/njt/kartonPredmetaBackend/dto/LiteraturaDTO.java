/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.TipLiterature;

/**
 *
 * @author Luka
 */

@Getter
@Setter
public class LiteraturaDTO implements DomainDTO{

    private int id;

    @NotBlank(message = "Naziv literature je obavezan")
    private String naziv;

    @NotBlank(message = "Autor je obavezan")
    private String autor;

    @NotNull(message = "Tip literature je obavezan")
    private TipLiterature tip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public TipLiterature getTip() {
        return tip;
    }

    public void setTip(TipLiterature tip) {
        this.tip = tip;
    }
    
    
}
