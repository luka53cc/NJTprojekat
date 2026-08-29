package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.PredispitnaObavezaDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.PredispitnaObaveza;

@Component
public class PredispitnaObavezaMapper implements BaseMapper<PredispitnaObavezaDTO, PredispitnaObaveza> {

    @Override
    public PredispitnaObaveza toEntity(PredispitnaObavezaDTO dto) {
        PredispitnaObaveza entity = new PredispitnaObaveza();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setBrojPoena(dto.getBrojPoena());
        return entity;
    }

    @Override
    public PredispitnaObavezaDTO toDTO(PredispitnaObaveza entity) {
        PredispitnaObavezaDTO dto = new PredispitnaObavezaDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setBrojPoena(entity.getBrojPoena());
        return dto;
    }
}