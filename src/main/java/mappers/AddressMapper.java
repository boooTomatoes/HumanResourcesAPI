package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.AddressDTO;
import persistence.entities.Address;
@Mapper
public interface AddressMapper extends BaseMapper<Address, AddressDTO>{
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
}
