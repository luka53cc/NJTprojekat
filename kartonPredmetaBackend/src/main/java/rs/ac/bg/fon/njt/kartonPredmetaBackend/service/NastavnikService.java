package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.NastavnikRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.NastavnikDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.BusinessException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.NotFoundException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper.NastavnikMapper;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Nastavnik;

import java.util.List;

@Service
public class NastavnikService {

    private final NastavnikRepository repository;
    private final NastavnikMapper mapper;

    public NastavnikService(NastavnikRepository repository, NastavnikMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<NastavnikDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public NastavnikDTO findById(int id) {
        Nastavnik entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nastavnik sa id " + id + " ne postoji"));
        return mapper.toDTO(entity);
    }

    public NastavnikDTO add(NastavnikDTO dto) {
        provericJedinstvenostEmaila(dto.getEmail(), null);
        Nastavnik entity = mapper.toEntity(dto);
        entity.setId(0);
        Nastavnik saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public NastavnikDTO update(int id, NastavnikDTO dto) {
        Nastavnik existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nastavnik sa id " + id + " ne postoji"));
        provericJedinstvenostEmaila(dto.getEmail(), id);
        existing.setIme(dto.getIme());
        existing.setPrezime(dto.getPrezime());
        existing.setEmail(dto.getEmail());
        existing.setZvanje(dto.getZvanje());
        Nastavnik saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Nastavnik sa id " + id + " ne postoji");
        }
        repository.deleteById(id);
    }

    private void provericJedinstvenostEmaila(String email, Integer trenutniId) {
        repository.findByEmail(email).ifPresent(postojeci -> {
            if (trenutniId == null || postojeci.getId() != trenutniId) {
                throw new BusinessException("Nastavnik sa email adresom " + email + " već postoji");
            }
        });
    }
}