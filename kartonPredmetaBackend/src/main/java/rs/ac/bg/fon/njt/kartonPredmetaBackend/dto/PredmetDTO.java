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

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public int getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(int godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public int getFondPredavanja() {
        return fondPredavanja;
    }

    public void setFondPredavanja(int fondPredavanja) {
        this.fondPredavanja = fondPredavanja;
    }

    public int getFondVezbi() {
        return fondVezbi;
    }

    public void setFondVezbi(int fondVezbi) {
        this.fondVezbi = fondVezbi;
    }

    public StatusPredmeta getStatus() {
        return status;
    }

    public void setStatus(StatusPredmeta status) {
        this.status = status;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getIshodiUcenja() {
        return ishodiUcenja;
    }

    public void setIshodiUcenja(String ishodiUcenja) {
        this.ishodiUcenja = ishodiUcenja;
    }

    public String getSadrzajPredavanja() {
        return sadrzajPredavanja;
    }

    public void setSadrzajPredavanja(String sadrzajPredavanja) {
        this.sadrzajPredavanja = sadrzajPredavanja;
    }

    public String getSadrzajVezbi() {
        return sadrzajVezbi;
    }

    public void setSadrzajVezbi(String sadrzajVezbi) {
        this.sadrzajVezbi = sadrzajVezbi;
    }

    public String getNacinPolaganja() {
        return nacinPolaganja;
    }

    public void setNacinPolaganja(String nacinPolaganja) {
        this.nacinPolaganja = nacinPolaganja;
    }

    public int getPoeniIspit() {
        return poeniIspit;
    }

    public void setPoeniIspit(int poeniIspit) {
        this.poeniIspit = poeniIspit;
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

    public Integer getModulId() {
        return modulId;
    }

    public void setModulId(Integer modulId) {
        this.modulId = modulId;
    }

    public String getModulNaziv() {
        return modulNaziv;
    }

    public void setModulNaziv(String modulNaziv) {
        this.modulNaziv = modulNaziv;
    }

    public Integer getNosilacId() {
        return nosilacId;
    }

    public void setNosilacId(Integer nosilacId) {
        this.nosilacId = nosilacId;
    }

    public String getNosilacImePrezime() {
        return nosilacImePrezime;
    }

    public void setNosilacImePrezime(String nosilacImePrezime) {
        this.nosilacImePrezime = nosilacImePrezime;
    }

    public Set<Integer> getNastavniciIds() {
        return nastavniciIds;
    }

    public void setNastavniciIds(Set<Integer> nastavniciIds) {
        this.nastavniciIds = nastavniciIds;
    }

    public List<String> getNastavniciImenaPrezimena() {
        return nastavniciImenaPrezimena;
    }

    public void setNastavniciImenaPrezimena(List<String> nastavniciImenaPrezimena) {
        this.nastavniciImenaPrezimena = nastavniciImenaPrezimena;
    }

    public List<LiteraturaDTO> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<LiteraturaDTO> literatura) {
        this.literatura = literatura;
    }

    public List<PredispitnaObavezaDTO> getPredispitneObaveze() {
        return predispitneObaveze;
    }

    public void setPredispitneObaveze(List<PredispitnaObavezaDTO> predispitneObaveze) {
        this.predispitneObaveze = predispitneObaveze;
    }
    
    
    
    
}
