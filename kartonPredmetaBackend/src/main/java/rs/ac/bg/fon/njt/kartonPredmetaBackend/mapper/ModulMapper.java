package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.ModulDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Modul;

@Component
public class ModulMapper implements BaseMapper<ModulDTO, Modul> {

    @Override
    public Modul toEntity(ModulDTO dto) {
        Modul entity = new Modul();
        entity.setId(dto.getId());
        entity.setNaziv(dto.getNaziv());
        // studijskiProgram se postavlja u servisu 
        return entity;
    }

    @Override
    public ModulDTO toDTO(Modul entity) {
        ModulDTO dto = new ModulDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        if (entity.getStudijskiProgram() != null) {
            dto.setStudijskiProgramId(entity.getStudijskiProgram().getId());
            dto.setStudijskiProgramNaziv(entity.getStudijskiProgram().getNaziv());
        }
        return dto;
    }
}