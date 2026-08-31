/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Luka
 */

@Getter
@Setter
public class ModulDTO implements DomainDTO{

    private int id;

    @NotBlank(message = "Naziv modula je obavezan")
    private String naziv;

    @NotNull(message = "Studijski program je obavezan")
    private Integer studijskiProgramId;

    private String studijskiProgramNaziv; // samo za prikaz, popunjava se pri čitanju

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

    public Integer getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Integer studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public String getStudijskiProgramNaziv() {
        return studijskiProgramNaziv;
    }

    public void setStudijskiProgramNaziv(String studijskiProgramNaziv) {
        this.studijskiProgramNaziv = studijskiProgramNaziv;
    }

    
}
