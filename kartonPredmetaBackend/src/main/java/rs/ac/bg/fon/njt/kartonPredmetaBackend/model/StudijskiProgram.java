package rs.ac.bg.fon.njt.kartonPredmetaBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 *
 * @author Luka
 */
@Entity
@Getter
@Setter
public class StudijskiProgram implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false, unique = true)
    private String skracenica; 

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

    public String getSkracenica() {
        return skracenica;
    }

    public void setSkracenica(String skracenica) {
        this.skracenica = skracenica;
    }
    
    
}