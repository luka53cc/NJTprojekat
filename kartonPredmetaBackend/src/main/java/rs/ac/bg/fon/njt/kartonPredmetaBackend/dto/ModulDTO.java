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
}
