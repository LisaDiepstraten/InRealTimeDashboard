package fontys.project.idk.application.business.mappers;


import fontys.project.idk.application.dal.entity.definitions.SelectItemEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.SelectItemEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SelectItemEventEntityMapper {
    SelectItemEventEntityMapper INSTANCE = Mappers.getMapper(SelectItemEventEntityMapper.class);

    @Mapping(target = "data.item_list_id", source = "itemListID")
    @Mapping(target = "data.item_list_name", source = "itemListName")
    @Mapping(target = "data.item", source = "item")
    SelectItemEvent toDomain(SelectItemEventEntity entity);

    @Mapping(target = "itemListID", source = "data.item_list_id")
    @Mapping(target = "itemListName", source = "data.item_list_name")
    @Mapping(target = "item", source = "data.item")
    SelectItemEventEntity toEntity(SelectItemEvent domain);

}
