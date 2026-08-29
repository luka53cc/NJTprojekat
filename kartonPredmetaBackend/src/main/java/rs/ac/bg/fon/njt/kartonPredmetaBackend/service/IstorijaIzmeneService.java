package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.IstorijaIzmeneRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.IstorijaIzmene;

import java.util.List;

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
}