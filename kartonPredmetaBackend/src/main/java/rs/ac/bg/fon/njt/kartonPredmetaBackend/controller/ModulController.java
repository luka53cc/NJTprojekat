package rs.ac.bg.fon.njt.kartonPredmetaBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.ModulDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.service.ModulService;

import java.util.List;

@RestController
@RequestMapping("/api/moduli")
public class ModulController {

    private final ModulService service;

    public ModulController(ModulService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ModulDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModulDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/po-programu/{studijskiProgramId}")
    public ResponseEntity<List<ModulDTO>> findByStudijskiProgram(@PathVariable int studijskiProgramId) {
        return ResponseEntity.ok(service.findByStudijskiProgram(studijskiProgramId));
    }

    @PostMapping
    public ResponseEntity<ModulDTO> add(@Valid @RequestBody ModulDTO dto) {
        ModulDTO saved = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModulDTO> update(@PathVariable int id, @Valid @RequestBody ModulDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}