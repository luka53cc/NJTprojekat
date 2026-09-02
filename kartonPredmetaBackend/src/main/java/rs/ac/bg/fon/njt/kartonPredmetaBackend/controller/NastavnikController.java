package rs.ac.bg.fon.njt.kartonPredmetaBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.NastavnikDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.service.NastavnikService;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnici")
@CrossOrigin(origins = "http://localhost:4200")
public class NastavnikController {

    private final NastavnikService service;

    public NastavnikController(NastavnikService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NastavnikDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<NastavnikDTO> add(@Valid @RequestBody NastavnikDTO dto) {
        NastavnikDTO saved = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikDTO> update(@PathVariable int id, @Valid @RequestBody NastavnikDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}