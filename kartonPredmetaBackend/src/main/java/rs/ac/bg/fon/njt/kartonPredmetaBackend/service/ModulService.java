package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.ModulRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.StudijskiProgramRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.ModulDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.NotFoundException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper.ModulMapper;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Modul;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.StudijskiProgram;

import java.util.List;

@Service
public class ModulService {

    private final ModulRepository repository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final ModulMapper mapper;

    public ModulService(ModulRepository repository, StudijskiProgramRepository studijskiProgramRepository, ModulMapper mapper) {
        this.repository = repository;
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.mapper = mapper;
    }

    public List<ModulDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public ModulDTO findById(int id) {
        Modul entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Modul sa id " + id + " ne postoji"));
        return mapper.toDTO(entity);
    }

    public List<ModulDTO> findByStudijskiProgram(int studijskiProgramId) {
        return mapper.toDTOList(repository.findByStudijskiProgramId(studijskiProgramId));
    }

    public ModulDTO add(ModulDTO dto) {
        Modul entity = mapper.toEntity(dto);
        entity.setId(0);
        entity.setStudijskiProgram(ucitajStudijskiProgram(dto.getStudijskiProgramId()));
        Modul saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public ModulDTO update(int id, ModulDTO dto) {
        Modul existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Modul sa id " + id + " ne postoji"));
        existing.setNaziv(dto.getNaziv());
        existing.setStudijskiProgram(ucitajStudijskiProgram(dto.getStudijskiProgramId()));
        Modul saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Modul sa id " + id + " ne postoji");
        }
        repository.deleteById(id);
    }

    private StudijskiProgram ucitajStudijskiProgram(int studijskiProgramId) {
        return studijskiProgramRepository.findById(studijskiProgramId)
                .orElseThrow(() -> new NotFoundException("Studijski program sa id " + studijskiProgramId + " ne postoji"));
    }
}