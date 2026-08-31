package rs.ac.bg.fon.njt.kartonPredmetaBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.StatusPredmeta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Luka
 */
@Entity
@Getter
@Setter
public class Predmet implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false, unique = true)
    private String sifra;

    @Column(nullable = false)
    private int godinaStudija; // 1-4

    @Column(nullable = false)
    private int semestar; // 1-8

    @Column(nullable = false)
    private int espb;

    @Column(nullable = false)
    private int fondPredavanja;

    @Column(nullable = false)
    private int fondVezbi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPredmeta status;

    @Column(columnDefinition = "TEXT")
    private String cilj;

    @Column(columnDefinition = "TEXT")
    private String ishodiUcenja;

    @Column(columnDefinition = "TEXT")
    private String sadrzajPredavanja;

    @Column(columnDefinition = "TEXT")
    private String sadrzajVezbi;

    @Column(columnDefinition = "TEXT")
    private String nacinPolaganja;

    @Column(nullable = false)
    private int poeniIspit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studijski_program_id", nullable = false)
    private StudijskiProgram studijskiProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modul_id")
    private Modul modul;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nosilac_id", nullable = false)
    private Nastavnik nosilac;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "predmet_nastavnik",
            joinColumns = @JoinColumn(name = "predmet_id"),
            inverseJoinColumns = @JoinColumn(name = "nastavnik_id"))
    private Set<Nastavnik> nastavnici = new HashSet<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Literatura> literatura = new ArrayList<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PredispitnaObaveza> predispitneObaveze = new ArrayList<>();

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

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    public Nastavnik getNosilac() {
        return nosilac;
    }

    public void setNosilac(Nastavnik nosilac) {
        this.nosilac = nosilac;
    }

    public Set<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(Set<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<Literatura> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<Literatura> literatura) {
        this.literatura = literatura;
    }

    public List<PredispitnaObaveza> getPredispitneObaveze() {
        return predispitneObaveze;
    }

    public void setPredispitneObaveze(List<PredispitnaObaveza> predispitneObaveze) {
        this.predispitneObaveze = predispitneObaveze;
    }
    
    
    
}