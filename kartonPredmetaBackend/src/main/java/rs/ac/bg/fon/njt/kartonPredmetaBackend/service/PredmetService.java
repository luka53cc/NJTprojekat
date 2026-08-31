package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredmetDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.BusinessException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.NotFoundException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper.PredmetMapper;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.*;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.LiteraturaDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredispitnaObavezaDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PredmetService {

    private final PredmetRepository repository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final ModulRepository modulRepository;
    private final NastavnikRepository nastavnikRepository;
    private final PredmetMapper mapper;
    private final IstorijaIzmeneService istorijaIzmeneService;

    public PredmetService(PredmetRepository repository,
                           StudijskiProgramRepository studijskiProgramRepository,
                           ModulRepository modulRepository,
                           NastavnikRepository nastavnikRepository,
                           PredmetMapper mapper,
                           IstorijaIzmeneService istorijaIzmeneService) {
        this.repository = repository;
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.modulRepository = modulRepository;
        this.nastavnikRepository = nastavnikRepository;
        this.mapper = mapper;
        this.istorijaIzmeneService = istorijaIzmeneService;
    }

    public List<PredmetDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public PredmetDTO findById(int id) {
        Predmet entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Predmet sa id " + id + " ne postoji"));
        return mapper.toDTO(entity);
    }
    
    

    public PredmetDTO add(PredmetDTO dto) {
        proveriPoslovnaPravila(dto);
        proveriJedinstvenostSifre(dto.getSifra(), null);

        Predmet entity = mapper.toEntity(dto);
        entity.setId(0);
        popuniVeze(entity, dto);

        Predmet saved = repository.save(entity);
        istorijaIzmeneService.zabelezi(saved.getId(), trenutniKorisnik(), "KREIRANJE",
                "Kreiran predmet: " + saved.getNaziv());

        return mapper.toDTO(saved);
    }

    public PredmetDTO update(int id, PredmetDTO dto) {
        Predmet existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Predmet sa id " + id + " ne postoji"));

        proveriPoslovnaPravila(dto);
        proveriJedinstvenostSifre(dto.getSifra(), id);

        existing.setNaziv(dto.getNaziv());
        existing.setSifra(dto.getSifra());
        existing.setGodinaStudija(dto.getGodinaStudija());
        existing.setSemestar(dto.getSemestar());
        existing.setEspb(dto.getEspb());
        existing.setFondPredavanja(dto.getFondPredavanja());
        existing.setFondVezbi(dto.getFondVezbi());
        existing.setStatus(dto.getStatus());
        existing.setCilj(dto.getCilj());
        existing.setIshodiUcenja(dto.getIshodiUcenja());
        existing.setSadrzajPredavanja(dto.getSadrzajPredavanja());
        existing.setSadrzajVezbi(dto.getSadrzajVezbi());
        existing.setNacinPolaganja(dto.getNacinPolaganja());
        existing.setPoeniIspit(dto.getPoeniIspit());

        popuniVeze(existing, dto);
        azurirajLiteraturu(existing, dto.getLiteratura());
        azurirajPredispitneObaveze(existing, dto.getPredispitneObaveze());

        Predmet saved = repository.save(existing);
        istorijaIzmeneService.zabelezi(saved.getId(), trenutniKorisnik(), "IZMENA",
                "Izmenjen predmet: " + saved.getNaziv());

        return mapper.toDTO(saved);
    }

    public void delete(int id) {
        Predmet entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Predmet sa id " + id + " ne postoji"));
        String naziv = entity.getNaziv();
        repository.deleteById(id);
        istorijaIzmeneService.zabelezi(id, trenutniKorisnik(), "BRISANJE", "Obrisan predmet: " + naziv);
    }
    
    public org.springframework.data.domain.Page<PredmetDTO> pretrazi(
        String naziv, String sifra, Integer godinaStudija, Integer semestar,
        rs.ac.bg.fon.njt.kartonPredmetaBackend.model.enums.StatusPredmeta status,
        Integer studijskiProgramId, Integer modulId,
        org.springframework.data.domain.Pageable pageable) {

    org.springframework.data.jpa.domain.Specification<Predmet> spec = (root, query, cb) -> cb.conjunction();

    if (naziv != null && !naziv.isBlank()) {
        spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("naziv")), "%" + naziv.toLowerCase() + "%"));
    }
    if (sifra != null && !sifra.isBlank()) {
        spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("sifra")), "%" + sifra.toLowerCase() + "%"));
    }
    if (godinaStudija != null) {
        spec = spec.and((root, query, cb) -> cb.equal(root.get("godinaStudija"), godinaStudija));
    }
    if (semestar != null) {
        spec = spec.and((root, query, cb) -> cb.equal(root.get("semestar"), semestar));
    }
    if (status != null) {
        spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
    }
    if (studijskiProgramId != null) {
        spec = spec.and((root, query, cb) -> cb.equal(root.get("studijskiProgram").get("id"), studijskiProgramId));
    }
    if (modulId != null) {
        spec = spec.and((root, query, cb) -> cb.equal(root.get("modul").get("id"), modulId));
    }

    return repository.findAll(spec, pageable).map(mapper::toDTO);
}
    

    // ---------- Poslovna pravila ----------

    private void proveriPoslovnaPravila(PredmetDTO dto) {
        int zbirPredispitnih = dto.getPredispitneObaveze().stream()
                .mapToInt(PredispitnaObavezaDTO::getBrojPoena)
                .sum();
        int ukupno = zbirPredispitnih + dto.getPoeniIspit();
        if (ukupno != 100) {
            throw new BusinessException("Zbir predispitnih poena i poena na ispitu mora biti tačno 100 (trenutno: " + ukupno + ")");
        }

        if (dto.getFondPredavanja() == 0 && dto.getFondVezbi() == 0) {
            throw new BusinessException("Fond časova (predavanja i vežbe) ne može biti nula za oba istovremeno");
        }
    }

    private void proveriJedinstvenostSifre(String sifra, Integer trenutniId) {
        repository.findBySifra(sifra).ifPresent(postojeci -> {
            if (trenutniId == null || postojeci.getId() != trenutniId) {
                throw new BusinessException("Predmet sa šifrom " + sifra + " već postoji");
            }
        });
    }

    // ---------- Pomoćne metode za povezivanje entiteta ----------

    private void popuniVeze(Predmet entity, PredmetDTO dto) {
        entity.setStudijskiProgram(studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                .orElseThrow(() -> new NotFoundException("Studijski program sa id " + dto.getStudijskiProgramId() + " ne postoji")));

        if (dto.getModulId() != null) {
            entity.setModul(modulRepository.findById(dto.getModulId())
                    .orElseThrow(() -> new NotFoundException("Modul sa id " + dto.getModulId() + " ne postoji")));
        } else {
            entity.setModul(null);
        }

        Nastavnik nosilac = nastavnikRepository.findById(dto.getNosilacId())
                .orElseThrow(() -> new NotFoundException("Nastavnik sa id " + dto.getNosilacId() + " ne postoji"));
        entity.setNosilac(nosilac);

        Set<Nastavnik> nastavnici = new HashSet<>();
        nastavnici.add(nosilac); // nosilac je uvek i deo skupa nastavnika na predmetu
        for (Integer nastavnikId : dto.getNastavniciIds()) {
            Nastavnik n = nastavnikRepository.findById(nastavnikId)
                    .orElseThrow(() -> new NotFoundException("Nastavnik sa id " + nastavnikId + " ne postoji"));
            nastavnici.add(n);
        }
        entity.setNastavnici(nastavnici);

        // Literatura i predispitne obaveze se postavljaju samo pri kreiranju ovde;
        // pri izmeni ih azurira azurirajLiteraturu/azurirajPredispitneObaveze
        if (entity.getId() == 0) {
            entity.setLiteratura(dto.getLiteratura().stream().map(l -> {
                Literatura lit = new Literatura();
                lit.setNaziv(l.getNaziv());
                lit.setAutor(l.getAutor());
                lit.setTip(l.getTip());
                lit.setPredmet(entity);
                return lit;
            }).collect(Collectors.toList()));

            entity.setPredispitneObaveze(dto.getPredispitneObaveze().stream().map(p -> {
                PredispitnaObaveza obaveza = new PredispitnaObaveza();
                obaveza.setNaziv(p.getNaziv());
                obaveza.setBrojPoena(p.getBrojPoena());
                obaveza.setPredmet(entity);
                return obaveza;
            }).collect(Collectors.toList()));
        }
    }

    private void azurirajLiteraturu(Predmet entity, List<LiteraturaDTO> literaturaDTOs) {
        entity.getLiteratura().clear();
        for (LiteraturaDTO l : literaturaDTOs) {
            Literatura lit = new Literatura();
            lit.setNaziv(l.getNaziv());
            lit.setAutor(l.getAutor());
            lit.setTip(l.getTip());
            lit.setPredmet(entity);
            entity.getLiteratura().add(lit);
        }
    }

    private void azurirajPredispitneObaveze(Predmet entity, List<PredispitnaObavezaDTO> obavezeDTOs) {
        entity.getPredispitneObaveze().clear();
        for (PredispitnaObavezaDTO p : obavezeDTOs) {
            PredispitnaObaveza obaveza = new PredispitnaObaveza();
            obaveza.setNaziv(p.getNaziv());
            obaveza.setBrojPoena(p.getBrojPoena());
            obaveza.setPredmet(entity);
            entity.getPredispitneObaveze().add(obaveza);
        }
    }

    private String trenutniKorisnik() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            return "sistem";
        }
    }
}