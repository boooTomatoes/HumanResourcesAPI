package mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import persistence.dto.BaseDTO;
import persistence.entities.BaseEntity;

import java.util.Collection;

public interface BaseMapper <E extends BaseEntity,D extends BaseDTO>{
    E toEntity(D dto);
    D toDTO(E entity);

    Collection<E> collectionToEntity(Collection<D> dtos);
    Collection<D> collectionToDto(Collection<E> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E partialUpdate(D dto, @MappingTarget E entity);

}
