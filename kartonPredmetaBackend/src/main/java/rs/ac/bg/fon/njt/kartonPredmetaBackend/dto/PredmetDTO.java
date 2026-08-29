/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.StatusPredmeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Luka
 */

@Getter
@Setter
public class PredmetDTO implements DomainDTO{

    private int id;

    @NotBlank(message = "Naziv predmeta je obavezan")
    private String naziv;

    @NotBlank(message = "Šifra predmeta je obavezna")
    private String sifra;

    @Min(value = 1, message = "Godina studija mora biti između 1 i 4")
    @Max(value = 4, message = "Godina studija mora biti između 1 i 4")
    private int godinaStudija;

    @Min(value = 1, message = "Semestar mora biti između 1 i 8")
    @Max(value = 8, message = "Semestar mora biti između 1 i 8")
    private int semestar;

    @Min(value = 1, message = "ESPB mora biti veći od 0")
    private int espb;

    @Min(value = 0, message = "Fond predavanja ne može biti negativan")
    private int fondPredavanja;

    @Min(value = 0, message = "Fond vežbi ne može biti negativan")
    private int fondVezbi;

    @NotNull(message = "Status predmeta je obavezan")
    private StatusPredmeta status;

    private String cilj;

    private String ishodiUcenja;

    private String sadrzajPredavanja;

    private String sadrzajVezbi;

    private String nacinPolaganja;

    @Min(value = 0, message = "Broj poena na ispitu ne može biti negativan")
    @Max(value = 100, message = "Broj poena na ispitu ne može biti veći od 100")
    private int poeniIspit;

    @NotNull(message = "Studijski program je obavezan")
    private Integer studijskiProgramId;

    private String studijskiProgramNaziv; 

    private Integer modulId; 

    private String modulNaziv; 

    @NotNull(message = "Nosilac predmeta je obavezan")
    private Integer nosilacId;

    private String nosilacImePrezime; 

    private Set<Integer> nastavniciIds = new HashSet<>();

    private List<String> nastavniciImenaPrezimena = new ArrayList<>(); 

    @Valid
    private List<LiteraturaDTO> literatura = new ArrayList<>();

    @Valid //proverava svaki el u toj listi ne samo njeno posotjanje
    private List<PredispitnaObavezaDTO> predispitneObaveze = new ArrayList<>();
}
