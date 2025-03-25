package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.PageVisitEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.PageVisitEvent;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:50+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class PageVisitEventEntityMapperImpl implements PageVisitEventEntityMapper {

    @Override
    public PageVisitEvent toDomain(PageVisitEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PageVisitEvent.PageVisitEventBuilder pageVisitEvent = PageVisitEvent.builder();

        pageVisitEvent.data( pageVisitEventEntityToData( entity ) );

        return pageVisitEvent.build();
    }

    @Override
    public PageVisitEventEntity toEntity(PageVisitEvent domain) {
        if ( domain == null ) {
            return null;
        }

        PageVisitEventEntity.PageVisitEventEntityBuilder pageVisitEventEntity = PageVisitEventEntity.builder();

        pageVisitEventEntity.pageType( domainDataPageType( domain ) );
        pageVisitEventEntity.pageLanguage( domainDataPageLanguage( domain ) );

        return pageVisitEventEntity.build();
    }

    protected PageVisitEvent.Data pageVisitEventEntityToData(PageVisitEventEntity pageVisitEventEntity) {
        if ( pageVisitEventEntity == null ) {
            return null;
        }

        PageVisitEvent.Data.DataBuilder data = PageVisitEvent.Data.builder();

        data.pageType( pageVisitEventEntity.getPageType() );
        data.pageLanguage( pageVisitEventEntity.getPageLanguage() );

        return data.build();
    }

    private String domainDataPageType(PageVisitEvent pageVisitEvent) {
        if ( pageVisitEvent == null ) {
            return null;
        }
        PageVisitEvent.Data data = pageVisitEvent.getData();
        if ( data == null ) {
            return null;
        }
        String pageType = data.getPageType();
        if ( pageType == null ) {
            return null;
        }
        return pageType;
    }

    private String domainDataPageLanguage(PageVisitEvent pageVisitEvent) {
        if ( pageVisitEvent == null ) {
            return null;
        }
        PageVisitEvent.Data data = pageVisitEvent.getData();
        if ( data == null ) {
            return null;
        }
        String pageLanguage = data.getPageLanguage();
        if ( pageLanguage == null ) {
            return null;
        }
        return pageLanguage;
    }
}
