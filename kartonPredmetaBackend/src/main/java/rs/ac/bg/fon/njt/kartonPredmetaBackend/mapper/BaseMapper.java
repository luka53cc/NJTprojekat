package rs.ac.bg.fon.njt.kartonPredmetaBackend.mapper;

import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.DomainDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.DomainEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Luka
 */

public interface BaseMapper<D extends DomainDTO, E extends DomainEntity> {

    E toEntity(D dto);

    D toDTO(E entity);

    default List<E> toEntityList(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default List<D> toDTOList(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}