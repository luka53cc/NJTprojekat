package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IstorijaIzmeneDTO {

    private int id;
    private int predmetId;
    private String korisnickoIme;
    private String tipAkcije;
    private String opis;
    private LocalDateTime datumVreme;


}