package rs.ac.bg.fon.njt.kartonPredmetaBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.Zvanje;
import java.io.Serializable;

/**
 *
 * @author Luka
 */
@Entity
@Getter
@Setter
public class Nastavnik implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Zvanje zvanje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Zvanje getZvanje() {
        return zvanje;
    }

    public void setZvanje(Zvanje zvanje) {
        this.zvanje = zvanje;
    }
    
    
}