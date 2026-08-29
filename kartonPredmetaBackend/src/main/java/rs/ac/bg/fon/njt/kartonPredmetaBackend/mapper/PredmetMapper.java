package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredmetDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Nastavnik;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Predmet;

import java.util.stream.Collectors;

@Component
public class PredmetMapper implements BaseMapper<PredmetDTO, Predmet> {

    private final LiteraturaMapper literaturaMapper;
    private final PredispitnaObavezaMapper predispitnaObavezaMapper;

    public PredmetMapper(LiteraturaMapper literaturaMapper, PredispitnaObavezaMapper predispitnaObavezaMapper) {
        this.literaturaMapper = literaturaMapper;
        this.predispitnaObavezaMapper = predispitnaObavezaMapper;
    }

    @Override
    public Predmet toEntity(PredmetDTO dto) {
        Predmet entity = new Predmet();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setSifra(dto.getSifra());
        entity.setGodinaStudija(dto.getGodinaStudija());
        entity.setSemestar(dto.getSemestar());
        entity.setEspb(dto.getEspb());
        entity.setFondPredavanja(dto.getFondPredavanja());
        entity.setFondVezbi(dto.getFondVezbi());
        entity.setStatus(dto.getStatus());
        entity.setCilj(dto.getCilj());
        entity.setIshodiUcenja(dto.getIshodiUcenja());
        entity.setSadrzajPredavanja(dto.getSadrzajPredavanja());
        entity.setSadrzajVezbi(dto.getSadrzajVezbi());
        entity.setNacinPolaganja(dto.getNacinPolaganja());
        entity.setPoeniIspit(dto.getPoeniIspit());
        // studijskiProgram, modul, nosilac, nastavnici, literatura, predispitneObaveze
        // se postavljaju u PredmetService, jer zahtevaju pristup repozitorijumima
        return entity;
    }

    @Override
    public PredmetDTO toDTO(Predmet entity) {
        PredmetDTO dto = new PredmetDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setSifra(entity.getSifra());
        dto.setGodinaStudija(entity.getGodinaStudija());
        dto.setSemestar(entity.getSemestar());
        dto.setEspb(entity.getEspb());
        dto.setFondPredavanja(entity.getFondPredavanja());
        dto.setFondVezbi(entity.getFondVezbi());
        dto.setStatus(entity.getStatus());
        dto.setCilj(entity.getCilj());
        dto.setIshodiUcenja(entity.getIshodiUcenja());
        dto.setSadrzajPredavanja(entity.getSadrzajPredavanja());
        dto.setSadrzajVezbi(entity.getSadrzajVezbi());
        dto.setNacinPolaganja(entity.getNacinPolaganja());
        dto.setPoeniIspit(entity.getPoeniIspit());

        if (entity.getStudijskiProgram() != null) {
            dto.setStudijskiProgramId(entity.getStudijskiProgram().getId());
            dto.setStudijskiProgramNaziv(entity.getStudijskiProgram().getNaziv());
        }

        if (entity.getModul() != null) {
            dto.setModulId(entity.getModul().getId());
            dto.setModulNaziv(entity.getModul().getNaziv());
        }

        if (entity.getNosilac() != null) {
            dto.setNosilacId(entity.getNosilac().getId());
            dto.setNosilacImePrezime(entity.getNosilac().getIme() + " " + entity.getNosilac().getPrezime());
        }

        if (entity.getNastavnici() != null) {
            dto.setNastavniciIds(entity.getNastavnici().stream()
                    .map(Nastavnik::getId)
                    .collect(Collectors.toSet()));
            dto.setNastavniciImenaPrezimena(entity.getNastavnici().stream()
                    .map(n -> n.getIme() + " " + n.getPrezime())
                    .collect(Collectors.toList()));
        }

        if (entity.getLiteratura() != null) {
            dto.setLiteratura(literaturaMapper.toDTOList(entity.getLiteratura()));
        }

        if (entity.getPredispitneObaveze() != null) {
            dto.setPredispitneObaveze(predispitnaObavezaMapper.toDTOList(entity.getPredispitneObaveze()));
        }

        return dto;
    }
}