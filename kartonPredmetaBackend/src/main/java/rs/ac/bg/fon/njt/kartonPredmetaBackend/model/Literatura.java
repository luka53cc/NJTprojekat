package rs.ac.bg.fon.njt.kartonPredmetaBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.TipLiterature;
import java.io.Serializable;

/**
 *
 * @author Luka
 */
@Entity
@Getter
@Setter
public class Literatura implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String autor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipLiterature tip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

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

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    
    
}