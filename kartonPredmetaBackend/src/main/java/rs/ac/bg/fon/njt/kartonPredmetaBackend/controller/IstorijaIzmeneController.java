package rs.ac.bg.fon.njt.kartonPredmetaBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.IstorijaIzmeneDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.service.IstorijaIzmeneService;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/istorija-izmena")
@CrossOrigin(origins = "http://localhost:4200")
public class IstorijaIzmeneController {

    private final IstorijaIzmeneService service;

    public IstorijaIzmeneController(IstorijaIzmeneService service) {
        this.service = service;
    }

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<IstorijaIzmeneDTO>> findByPredmet(@PathVariable int predmetId) {
        return ResponseEntity.ok(service.findByPredmetKaoDTO(predmetId));
    }
}