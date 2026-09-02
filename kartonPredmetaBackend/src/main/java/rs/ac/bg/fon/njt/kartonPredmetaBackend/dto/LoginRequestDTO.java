package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Korisničko ime je obavezno")
    private String korisnickoIme;

    @NotBlank(message = "Lozinka je obavezna")
    private String lozinka;




    
    
}