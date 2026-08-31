package rs.ac.bg.fon.njt.kartonPredmetaBackend.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredmetDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.StatusPredmeta;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.service.PredmetService;
import org.springframework.http.MediaType;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.pdf.KartonPdfGenerator;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
public class PredmetController {

    private final PredmetService service;
    private final KartonPdfGenerator pdfGenerator;

    public PredmetController(PredmetService service, KartonPdfGenerator pdfGenerator) {
        this.service = service;
        this.pdfGenerator = pdfGenerator;
    }
    
    
    @GetMapping
    public ResponseEntity<List<PredmetDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/pretraga")
    public ResponseEntity<Page<PredmetDTO>> pretrazi(
            @RequestParam(required = false) String naziv,
            @RequestParam(required = false) String sifra,
            @RequestParam(required = false) Integer godinaStudija,
            @RequestParam(required = false) Integer semestar,
            @RequestParam(required = false) StatusPredmeta status,
            @RequestParam(required = false) Integer studijskiProgramId,
            @RequestParam(required = false) Integer modulId,
            @PageableDefault(size = 10, sort = "naziv") Pageable pageable) {

        return ResponseEntity.ok(service.pretrazi(naziv, sifra, godinaStudija, semestar,
                status, studijskiProgramId, modulId, pageable));
    }

    @PostMapping
    public ResponseEntity<PredmetDTO> add(@Valid @RequestBody PredmetDTO dto) {
        PredmetDTO saved = service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredmetDTO> update(@PathVariable int id, @Valid @RequestBody PredmetDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generisiPdf(@PathVariable int id) {
    PredmetDTO predmet = service.findById(id);
    byte[] pdf = pdfGenerator.generisi(predmet);

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header("Content-Disposition", "attachment; filename=karton_" + predmet.getSifra() + ".pdf")
            .body(pdf);
}
}