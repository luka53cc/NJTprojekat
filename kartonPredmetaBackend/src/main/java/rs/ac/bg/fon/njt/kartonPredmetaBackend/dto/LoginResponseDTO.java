package rs.ac.bg.fon.njt.kartonPredmetaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private String korisnickoIme;
    private String uloga;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    /*public LoginResponseDTO(String token, String korisnickoIme, String uloga) {
        this.token = token;
        this.korisnickoIme = korisnickoIme;
        this.uloga = uloga;
    }*/
    
    
    
}