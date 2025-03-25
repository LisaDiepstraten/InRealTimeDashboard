package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ElementInteractionEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.ElementInteractionEvent;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ElementInteractionEventEntityMapper{
    ElementInteractionEventEntityMapper INSTANCE = Mappers.getMapper(ElementInteractionEventEntityMapper .class);

    @Mapping(target = "data.elementName", source = "elementName")
    ElementInteractionEvent toDomain(ElementInteractionEventEntity entity);

    @Mapping(target = "elementName", source = "data.elementName")
    @Mapping(target = "interactionType", ignore = true)
    ElementInteractionEventEntity toEntity(ElementInteractionEvent domain);

    default ElementInteractionEvent.Data toDomainData(String elementName) {
        return ElementInteractionEvent.Data.builder()
                .elementName(elementName)
                .build();
    }

    default String toEntityData(ElementInteractionEvent.Data data) {
        return data != null ? data.getElementName() : null;
    }
}
