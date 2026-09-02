package rs.ac.bg.fon.njt.kartonPredmetaBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.StudijskiProgramDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.service.StudijskiProgramService;

import java.util.List;

@RestController
@RequestMapping("/api/studijski-programi")
@CrossOrigin(origins = "http://localhost:4200")
public class StudijskiProgramController {

    private final StudijskiProgramService service;

    public StudijskiProgramController(StudijskiProgramService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StudijskiProgramDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudijskiProgramDTO> add(@Valid @RequestBody StudijskiProgramDTO dto) {
        StudijskiProgramDTO saved = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable int id, @Valid @RequestBody StudijskiProgramDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}