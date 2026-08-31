package rs.ac.bg.fon.njt.kartonPredmetaBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Luka
 */
@Entity
@Getter
@Setter
public class IstorijaIzmene implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int predmetId; // ne pravimo @ManyToOne namerno, jer istorija treba da opstane i ako se predmet kasnije obriše

    @Column(nullable = false)
    private String korisnickoIme;

    @Column(nullable = false)
    private String tipAkcije; // "KREIRANJE", "IZMENA", "BRISANJE"

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(nullable = false)
    private LocalDateTime datumVreme;

    @PrePersist
    protected void onCreate() {
        this.datumVreme = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getTipAkcije() {
        return tipAkcije;
    }

    public void setTipAkcije(String tipAkcije) {
        this.tipAkcije = tipAkcije;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }
    
    
}