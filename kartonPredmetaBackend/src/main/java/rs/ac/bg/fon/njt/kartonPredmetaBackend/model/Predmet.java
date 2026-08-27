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
}