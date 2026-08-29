package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.NastavnikDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Nastavnik;

@Component
public class NastavnikMapper implements BaseMapper<NastavnikDTO, Nastavnik> {

    @Override
    public Nastavnik toEntity(NastavnikDTO dto) {
        Nastavnik entity = new Nastavnik();
        entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setEmail(dto.getEmail());
        entity.setZvanje(dto.getZvanje());
        return entity;
    }

    @Override
    public NastavnikDTO toDTO(Nastavnik entity) {
        NastavnikDTO dto = new NastavnikDTO();
        dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        dto.setEmail(entity.getEmail());
        dto.setZvanje(entity.getZvanje());
        return dto;
    }
}