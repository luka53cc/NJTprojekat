package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.StudijskiProgramRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.StudijskiProgramDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.NotFoundException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper.StudijskiProgramMapper;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.StudijskiProgram;

import java.util.List;

@Service
public class StudijskiProgramService {

    private final StudijskiProgramRepository repository;
    private final StudijskiProgramMapper mapper;

    public StudijskiProgramService(StudijskiProgramRepository repository, StudijskiProgramMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<StudijskiProgramDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public StudijskiProgramDTO findById(int id) {
        StudijskiProgram entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studijski program sa id " + id + " ne postoji"));
        return mapper.toDTO(entity);
    }

    public StudijskiProgramDTO add(StudijskiProgramDTO dto) {
        StudijskiProgram entity = mapper.toEntity(dto);
        entity.setId(0); // osiguravamo da je ovo kreiranje novog zapisa, ne izmena postojećeg
        StudijskiProgram saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public StudijskiProgramDTO update(int id, StudijskiProgramDTO dto) {
        StudijskiProgram existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studijski program sa id " + id + " ne postoji"));
        existing.setNaziv(dto.getNaziv());
        existing.setSkracenica(dto.getSkracenica());
        StudijskiProgram saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Studijski program sa id " + id + " ne postoji");
        }
        repository.deleteById(id);
    }
}