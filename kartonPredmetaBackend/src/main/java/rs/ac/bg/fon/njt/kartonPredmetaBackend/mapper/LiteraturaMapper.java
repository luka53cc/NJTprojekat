package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.LiteraturaDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Literatura;

@Component
public class LiteraturaMapper implements BaseMapper<LiteraturaDTO, Literatura> {

    @Override
    public Literatura toEntity(LiteraturaDTO dto) {
        Literatura entity = new Literatura();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setAutor(dto.getAutor());
        entity.setTip(dto.getTip());
        return entity;
    }

    @Override
    public LiteraturaDTO toDTO(Literatura entity) {
        LiteraturaDTO dto = new LiteraturaDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setAutor(entity.getAutor());
        dto.setTip(entity.getTip());
        return dto;
    }
}