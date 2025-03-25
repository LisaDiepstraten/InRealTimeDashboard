package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemEntityMapper {
    ItemEntityMapper INSTANCE = Mappers.getMapper(ItemEntityMapper.class);

    Item toDomain(ItemEntity entity);
    ItemEntity toEntity(Item item);
}