package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.StudijskiProgramDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.StudijskiProgram;

@Component
public class StudijskiProgramMapper implements BaseMapper<StudijskiProgramDTO, StudijskiProgram> {

    @Override
    public StudijskiProgram toEntity(StudijskiProgramDTO dto) {
        StudijskiProgram entity = new StudijskiProgram();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        entity.setSkracenica(dto.getSkracenica());
        return entity;
    }

    @Override
    public StudijskiProgramDTO toDTO(StudijskiProgram entity) {
        StudijskiProgramDTO dto = new StudijskiProgramDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setSkracenica(entity.getSkracenica());
        return dto;
    }
}