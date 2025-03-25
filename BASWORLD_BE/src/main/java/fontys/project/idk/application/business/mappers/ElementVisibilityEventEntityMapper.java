package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ElementVisibilityEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.ElementVisibilityEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ElementVisibilityEventEntityMapper {
    ElementVisibilityEventEntityMapper INSTANCE = Mappers.getMapper(ElementVisibilityEventEntityMapper.class);

    @Mapping(target = "data.elementName", source = "elementName")
    ElementVisibilityEvent toDomain(ElementVisibilityEventEntity entity);
    @Mapping(target = "elementName", source = "data.elementName")
    ElementVisibilityEventEntity toEntity(ElementVisibilityEvent domain);
}
