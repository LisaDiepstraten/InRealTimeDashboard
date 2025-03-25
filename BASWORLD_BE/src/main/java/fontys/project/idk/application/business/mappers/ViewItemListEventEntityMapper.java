package fontys.project.idk.application.business.mappers;


import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import fontys.project.idk.application.dal.entity.definitions.ViewItemListEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import fontys.project.idk.application.models.bas_data.definitions.ViewItemListEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ViewItemListEventEntityMapper {
    ViewItemListEventEntityMapper INSTANCE = Mappers.getMapper(ViewItemListEventEntityMapper.class);

    @Mapping(target = "data.item_list_id", source = "itemListID")
    @Mapping(target = "data.item_list_name", source = "itemListName")
    @Mapping(target = "data.items", source = "items", qualifiedByName = "mapSetToList")
    ViewItemListEvent toDomain(ViewItemListEventEntity entity);

    @Mapping(target = "itemListID", source = "data.item_list_id")
    @Mapping(target = "itemListName", source = "data.item_list_name")
    @Mapping(target = "items", source = "data.items", qualifiedByName = "mapListToSet")
    ViewItemListEventEntity toEntity(ViewItemListEvent domain);

    @Named("mapListToSet")
    default Set<ItemEntity> mapListToSet(ArrayList<Item> items) {
        return items.stream()
                .map(ItemEntityMapper.INSTANCE::toEntity)
                .collect(Collectors.toSet());
    }

    @Named("mapSetToList")
    default ArrayList<Item> mapSetToList(Set<ItemEntity> items) {
        return items.stream()
                .map(ItemEntityMapper.INSTANCE::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
