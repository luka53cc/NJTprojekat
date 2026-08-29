package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudijskiProgramDTO implements DomainDTO{

    private int id;

    @NotBlank(message = "Naziv studijskog programa je obavezan")
    private String naziv;

    @NotBlank(message = "Skraćenica studijskog programa je obavezna")
    private String skracenica;
}