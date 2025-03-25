package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ElementVisibilityEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.ElementVisibilityEvent;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:51+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class ElementVisibilityEventEntityMapperImpl implements ElementVisibilityEventEntityMapper {

    @Override
    public ElementVisibilityEvent toDomain(ElementVisibilityEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ElementVisibilityEvent.ElementVisibilityEventBuilder elementVisibilityEvent = ElementVisibilityEvent.builder();

        elementVisibilityEvent.data( elementVisibilityEventEntityToData( entity ) );

        return elementVisibilityEvent.build();
    }

    @Override
    public ElementVisibilityEventEntity toEntity(ElementVisibilityEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ElementVisibilityEventEntity.ElementVisibilityEventEntityBuilder elementVisibilityEventEntity = ElementVisibilityEventEntity.builder();

        elementVisibilityEventEntity.elementName( domainDataElementName( domain ) );

        return elementVisibilityEventEntity.build();
    }

    protected ElementVisibilityEvent.Data elementVisibilityEventEntityToData(ElementVisibilityEventEntity elementVisibilityEventEntity) {
        if ( elementVisibilityEventEntity == null ) {
            return null;
        }

        ElementVisibilityEvent.Data.DataBuilder data = ElementVisibilityEvent.Data.builder();

        data.elementName( elementVisibilityEventEntity.getElementName() );

        return data.build();
    }

    private String domainDataElementName(ElementVisibilityEvent elementVisibilityEvent) {
        if ( elementVisibilityEvent == null ) {
            return null;
        }
        ElementVisibilityEvent.Data data = elementVisibilityEvent.getData();
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
