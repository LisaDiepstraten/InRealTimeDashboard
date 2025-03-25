package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.OtherEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.OtherEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:50+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class OtherEventEntityMapperImpl implements OtherEventEntityMapper {

    @Override
    public OtherEvent toDomain(OtherEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OtherEvent.OtherEventBuilder otherEvent = OtherEvent.builder();

        return otherEvent.build();
    }

    @Override
    public OtherEventEntity toEntity(OtherEvent domain) {
        if ( domain == null ) {
            return null;
        }

        OtherEventEntity otherEventEntity = new OtherEventEntity();

        otherEventEntity.setEventName( domain.getEventName() );
        Map<String, Object> map = domain.getAdditionalData();
        if ( map != null ) {
            otherEventEntity.setAdditionalData( new LinkedHashMap<String, Object>( map ) );
        }

        return otherEventEntity;
    }
}
