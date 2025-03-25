package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ElementInteractionEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.ElementInteractionEvent;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:50+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class ElementInteractionEventEntityMapperImpl implements ElementInteractionEventEntityMapper {

    @Override
    public ElementInteractionEvent toDomain(ElementInteractionEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ElementInteractionEvent.ElementInteractionEventBuilder elementInteractionEvent = ElementInteractionEvent.builder();

        elementInteractionEvent.data( elementInteractionEventEntityToData( entity ) );

        return elementInteractionEvent.build();
    }

    @Override
    public ElementInteractionEventEntity toEntity(ElementInteractionEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ElementInteractionEventEntity.ElementInteractionEventEntityBuilder elementInteractionEventEntity = ElementInteractionEventEntity.builder();

        elementInteractionEventEntity.elementName( domainDataElementName( domain ) );

        return elementInteractionEventEntity.build();
    }

    protected ElementInteractionEvent.Data elementInteractionEventEntityToData(ElementInteractionEventEntity elementInteractionEventEntity) {
        if ( elementInteractionEventEntity == null ) {
            return null;
        }

        ElementInteractionEvent.Data.DataBuilder data = ElementInteractionEvent.Data.builder();

        data.elementName( elementInteractionEventEntity.getElementName() );

        return data.build();
    }

    private String domainDataElementName(ElementInteractionEvent elementInteractionEvent) {
        if ( elementInteractionEvent == null ) {
            return null;
        }
        ElementInteractionEvent.Data data = elementInteractionEvent.getData();
        if ( data == null ) {
            return null;
        }
        String elementName = data.getElementName();
        if ( elementName == null ) {
            return null;
        }
        return elementName;
    }
}
