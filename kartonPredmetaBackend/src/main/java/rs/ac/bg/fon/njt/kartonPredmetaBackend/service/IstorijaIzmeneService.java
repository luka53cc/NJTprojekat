package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.IstorijaIzmeneRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.IstorijaIzmene;

import java.util.List;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.IstorijaIzmeneDTO;

@Service
public class IstorijaIzmeneService {

    private final IstorijaIzmeneRepository repository;

    public IstorijaIzmeneService(IstorijaIzmeneRepository repository) {
        this.repository = repository;
    }

    public void zabelezi(int predmetId, String korisnickoIme, String tipAkcije, String opis) {
        IstorijaIzmene istorija = new IstorijaIzmene();
        istorija.setPredmetId(predmetId);
        istorija.setKorisnickoIme(korisnickoIme);
        istorija.setTipAkcije(tipAkcije);
        istorija.setOpis(opis);
        repository.save(istorija);
    }

    public List<IstorijaIzmene> findByPredmet(int predmetId) {
        return repository.findByPredmetIdOrderByDatumVremeDesc(predmetId);
    }
    public List<IstorijaIzmeneDTO> findByPredmetKaoDTO(int predmetId) {
    return findByPredmet(predmetId).stream().map(this::toDTO).collect(java.util.stream.Collectors.toList());
}

    private IstorijaIzmeneDTO toDTO(rs.ac.bg.fon.njt.kartonPredmetaBackend.model.IstorijaIzmene entity) {
        IstorijaIzmeneDTO dto = new IstorijaIzmeneDTO();
        dto.setId(entity.getId());
        dto.setPredmetId(entity.getPredmetId());
        dto.setKorisnickoIme(entity.getKorisnickoIme());
        dto.setTipAkcije(entity.getTipAkcije());
        dto.setOpis(entity.getOpis());
        dto.setDatumVreme(entity.getDatumVreme());
        return dto;
    }
}